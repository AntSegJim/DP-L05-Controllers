
package services;

import java.sql.Date;
import java.util.Collection;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Category;
import domain.Filter;
import domain.Warranty;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class FilterServiceTest extends AbstractTest {

	@Autowired
	private FilterService	filterService;
	@Autowired
	private WarrantyService	warrantyService;
	@Autowired
	private CategoryService	categoryService;


	@Test
	public void createFilter() {
		super.authenticate("admin");
		Filter f;

		final Warranty warranty, savedWarranty;
		warranty = this.warrantyService.create();
		final Collection<String> laws = warranty.getLaws();
		laws.add("Ley1");
		final Collection<String> terms = warranty.getTerms();
		terms.add("Term1");
		warranty.setLaws(laws);
		warranty.setTerms(terms);
		warranty.setTitle("TituloWarranty");
		warranty.setDraftMode(1);
		savedWarranty = this.warrantyService.save(warranty);

		Category category, savedCategory;
		category = this.categoryService.create();
		category.setName("CategoriaPrimera");
		category.setParent(category);
		savedCategory = this.categoryService.save(category);

		f = this.filterService.create();
		f.setAddress("Direccion");
		f.setDescription("Description");
		f.setEndDate(Date.valueOf("2018-12-12"));
		f.setHighPrice(12.);
		f.setLowPrice(10.);
		f.setStartDate(Date.valueOf("2017-12-12"));
		f.setWarranty(savedWarranty);
		f.setCategory(savedCategory);
		Assert.isTrue(f.getDescription().equals("Description"), "FilterServiceTest.create -> Mal");
		super.authenticate(null);
	}
	@Test
	public void saveFilter() {
		super.authenticate("admin");
		Filter f;
		final Filter savedF;

		final Warranty warranty, savedWarranty;
		warranty = this.warrantyService.create();
		final Collection<String> laws = warranty.getLaws();
		laws.add("Ley1");
		final Collection<String> terms = warranty.getTerms();
		terms.add("Term1");
		warranty.setLaws(laws);
		warranty.setTerms(terms);
		warranty.setTitle("TituloWarranty");
		warranty.setDraftMode(1);
		savedWarranty = this.warrantyService.save(warranty);

		Category category, savedCategory;
		category = this.categoryService.create();
		category.setName("CategoriaPrimera");
		category.setParent(category);
		savedCategory = this.categoryService.save(category);

		f = this.filterService.create();
		f.setAddress("Direccion");
		f.setDescription("Description");
		f.setEndDate(Date.valueOf("2018-12-12"));
		f.setHighPrice(12.);
		f.setLowPrice(10.);
		f.setStartDate(Date.valueOf("2017-12-12"));
		f.setWarranty(savedWarranty);
		f.setCategory(savedCategory);

		savedF = this.filterService.save(f);
		final Collection<Filter> fs = this.filterService.findAll();

		Assert.isTrue(savedF.getWarranty().equals(savedWarranty));
		Assert.isTrue(fs.contains(savedF), "FilterServiceTest.save -> Mal");
		super.authenticate(null);
	}
	@Test
	public void deleteFilter() {
		super.authenticate("admin");
		Filter f;
		final Filter savedF;

		final Warranty warranty, savedWarranty;
		warranty = this.warrantyService.create();
		final Collection<String> laws = warranty.getLaws();
		laws.add("Ley1");
		final Collection<String> terms = warranty.getTerms();
		terms.add("Term1");
		warranty.setLaws(laws);
		warranty.setTerms(terms);
		warranty.setTitle("TituloWarranty");
		warranty.setDraftMode(1);
		savedWarranty = this.warrantyService.save(warranty);

		Category category, savedCategory;
		category = this.categoryService.create();
		category.setName("CategoriaPrimera");
		category.setParent(category);
		savedCategory = this.categoryService.save(category);

		f = this.filterService.create();
		f.setAddress("Direccion");
		f.setDescription("Description");
		f.setEndDate(Date.valueOf("2018-12-12"));
		f.setHighPrice(12.);
		f.setLowPrice(10.);
		f.setStartDate(Date.valueOf("2017-12-12"));
		f.setWarranty(savedWarranty);
		f.setCategory(savedCategory);

		savedF = this.filterService.save(f);
		this.filterService.delete(savedF);
		final Collection<Filter> fs = this.filterService.findAll();
		Assert.isTrue(!fs.contains(savedF), "FilterServiceTest.delete-> Mal");
		super.authenticate(null);
	}
}
