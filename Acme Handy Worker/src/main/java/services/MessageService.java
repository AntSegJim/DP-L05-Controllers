
package services;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.MessageRepository;
import security.LoginService;
import security.UserAccount;
import domain.Actor;
import domain.Message;
import domain.MessageBox;

@Service
@Transactional
public class MessageService {

	@Autowired
	private MessageRepository	messageRepository;

	@Autowired
	private ActorService		actorService;

	@Autowired
	private MessageBoxService	messageBoxService;

	@Autowired
	private SpamWordService		spamWordService;


	public Message create() {
		final Message message = new Message();

		message.setMoment(new Date());
		message.setSubject("");
		message.setBody("");
		message.setPriority(0);
		message.setTag("");
		message.setSender(new Actor());
		message.setReceiver(new Actor());
		message.setEmailReceiver("");
		return message;
	}

	public Message save(final Message message) {

		final UserAccount user = LoginService.getPrincipal();
		final Actor actor = this.actorService.getActorByUserAccount(user.getId());

		message.setSender(actor);

		Assert.isTrue(message.getSender().equals(actor));
		Assert.isTrue(message != null && message.getMoment() != null && message.getPriority() >= 0 && message.getPriority() <= 2 && message.getSender() != null && message.getReceiver() != null);
		Assert.isTrue(message.getEmailReceiver() != null && message.getEmailReceiver() != "");

		final Actor receiver = this.actorService.getActorByEmail(message.getEmailReceiver());
		message.setReceiver(receiver);

		return this.messageRepository.save(message);
	}

	public void sendMessage(final Message message) {
		this.save(message);

		final MessageBox outBox = this.messageBoxService.getOutBox(message.getSender().getId());
		outBox.getMessages().add(message);

		if (this.auxEsSpam(message)) {
			final MessageBox spamBox = this.messageBoxService.getSpamBox(message.getReceiver().getId());
			spamBox.getMessages().add(message);
		} else {
			final MessageBox inBox = this.messageBoxService.getInBox(message.getReceiver().getId());
			inBox.getMessages().add(message);
		}

	}

	public Collection<Message> findAll() {
		return this.messageRepository.findAll();
	}

	public Message findOne(final int id) {
		return this.messageRepository.findOne(id);
	}

	public void delete(final Message message) {
		final UserAccount user = LoginService.getPrincipal();
		final Actor actor = this.actorService.getActorByUserAccount(user.getId());

		Assert.isTrue(user != null);
		Assert.isTrue(actor != null);
		Assert.isTrue(message.getSender().equals(actor));

		final MessageBox trashBox = this.messageBoxService.getTrashBox(message.getSender().getId());
		Assert.isTrue(trashBox.getActor().equals(actor));
		if (trashBox.getMessages().contains(message)) {
			final List<MessageBox> boxes = this.messageBoxService.findMessageBoxActor(actor.getId());
			for (int i = 0; i < boxes.size(); i++)
				if (boxes.get(i).getMessages().contains(message))
					boxes.get(i).getMessages().remove(message);
			this.messageRepository.delete(message);
		} else
			trashBox.getMessages().add(message);
	}

	public Collection<Message> getMessageByBox(final int id) {
		return this.messageRepository.getMessagesByBox(id);
	}

	public Boolean auxEsSpam(final Message message) {
		Boolean res = false;
		final Collection<String> spamWords = this.spamWordService.getNamesOfSpamWord();
		final String[] contenido = message.getBody().replace(".", "").replace(",", "").split(" ");

		for (int i = 0; i < contenido.length; i++) {
			final String palabra = contenido[i];
			if (spamWords.contains(palabra)) {
				res = true;
				break;
			}
		}
		return res;
	}

}
