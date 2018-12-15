
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.PictureRepository;
import domain.Picture;

@Service
@Transactional
public class PictureService {

	@Autowired
	private PictureRepository	PRepo;


	//Metodo create
	public Picture create() {
		final Picture pic = new Picture();
		pic.setPicture("");
		return pic;
	}

	//Metodo findAll
	public Collection<Picture> finaAll() {
		return this.PRepo.findAll();
	}
	public Picture findOne(final int PictureId) {
		return this.PRepo.findOne(PictureId);
	}
	public Picture save(final Picture picture) {
		Assert.isTrue(picture != null && picture.getPicture() != null && picture.getPicture() != "");
		return this.PRepo.save(picture);
	}
	public void delete(final Picture picture) {
		this.PRepo.delete(picture);
	}

}
