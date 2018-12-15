
package services;

import java.util.Collection;
import java.util.HashSet;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.CategoryRepository;
import security.LoginService;
import security.UserAccount;
import domain.Category;

@Service
@Transactional
public class CategoryService {

	@Autowired
	private CategoryRepository	categoryRepository;


	//private final Category		c	= this.rootCategory();

	//creating
	/*
	 * public Category create(final String name, final Category parent, final Collection<Category> soon) {
	 * final Category category = new Category();
	 * category.setName(name);
	 * category.setParent(parent);
	 * category.setSoon(soon);
	 * return category;
	 * }
	 */

	public Category create() {
		final Category category = new Category();
		category.setName("");
		//PREGUNTAR
		category.setParent(this.rootCategory());
		category.setSoon(new HashSet<Category>());
		return category;
	}

	//listing
	public Collection<Category> findAll() {
		return this.categoryRepository.findAll();
	}

	public Category findOne(final int categoryId) {
		return this.categoryRepository.findOne(categoryId);
	}

	//updating
	public Category save(final Category category) {
		Assert.isTrue(category.getName() != null && category.getName() != "" && !category.getSoon().contains(category.getParent()), "CategoryService.save -> Primero");
		Assert.isTrue(!(category.getName().equals(null)), "CategoryService.save -> Name null");
		Assert.isTrue(!(category.getName() == ""), "CategoryService.save -> Name");
		Assert.isTrue(!(category.getParent().equals(null)), "CategoryService.save -> Parent");
		final Collection<String> names = this.categoryRepository.namesCategory();
		Assert.isTrue(!names.contains(category.getName().toUpperCase()), "CategoryService.save -> Fallo");
		return this.categoryRepository.save(category);
	}
	//deleting
	public void delete(final Category category) {
		final UserAccount user = LoginService.getPrincipal();
		Assert.isTrue(user.getAuthorities().iterator().next().getAuthority().equals("ADMIN"), "CategoryService.delete -> Authority");
		Assert.isTrue(!((category.getName() == "CATEGORY")));

		this.categoryRepository.delete(category);

	}

	public Category rootCategory() {
		return this.categoryRepository.rootCategory();
	}

}
