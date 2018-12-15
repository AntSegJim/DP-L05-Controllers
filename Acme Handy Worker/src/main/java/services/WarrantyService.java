
package services;

import java.util.Collection;
import java.util.HashSet;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.WarrantyRepository;
import domain.Warranty;

@Service
@Transactional
public class WarrantyService {

	@Autowired
	private WarrantyRepository	warrantyRepository;


	public Warranty create() {
		final Warranty w = new Warranty();
		w.setDraftMode(0);
		w.setLaws(new HashSet<String>());
		w.setTerms(new HashSet<String>());
		w.setTitle("");
		return w;
	}
	public Collection<Warranty> findAll() {
		return this.warrantyRepository.findAll();
	}

	public Warranty findOne(final Integer id) {
		return this.warrantyRepository.findOne(id);
	}
	public Warranty save(final Warranty w) {
		Assert.isTrue(w.getDraftMode() == 1 && w.getTitle() != null && w.getTitle() != "" && w.getLaws() != null && w.getTerms() != null, "WarrantyService.save -> ERROR");
		return this.warrantyRepository.save(w);
	}
	public void delete(final Warranty w) {
		Assert.isTrue(w.getDraftMode() == 1, "WarrantyService.delete -> ERROR");
		this.warrantyRepository.delete(w);
	}
}
