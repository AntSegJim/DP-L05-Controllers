
package services;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

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
import domain.Finder;
import domain.Warranty;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class FinderServiceTest extends AbstractTest {

	@Autowired
	private FinderService	finderService;
	@Autowired
	private FilterService	filterService;
	@Autowired
	private CategoryService	categoryService;
	@Autowired
	private WarrantyService	warrantyService;


	//---------------------- Test ----------------------
	@Test
	public void testCreateFinder() {
		Finder f;
		f = this.finderService.create();
		f.setMoment(new Date());
		Assert.isTrue(f.getMoment() != null);
	}
	@Test
	public void testSaveFinder() {
		super.authenticate("admin");
		Finder f, savedF;
		f = this.finderService.create();
		f.setMoment(new Date());
		final Filter filter = f.getFilter();

		final Category c = filter.getCategory();
		c.setName("hola");
		c.setSoon(new HashSet<Category>());
		c.setParent(this.categoryService.findOne(5625));

		final Warranty w, savedw;
		w = this.warrantyService.create();
		w.setDraftMode(1);
		final Collection<String> laws = new HashSet<String>();
		laws.add("law1");
		w.setLaws(laws);
		final Collection<String> terms = new HashSet<String>();
		laws.add("term1");
		w.setTerms(terms);
		w.setTitle("TitleWarranty");
		savedw = this.warrantyService.save(w);

		filter.setCategory(this.categoryService.save(c));
		filter.setWarranty(savedw);
		final Filter savedFilter = this.filterService.save(filter);
		f.setFilter(savedFilter);

		savedF = this.finderService.save(f);
		final Collection<Finder> fs = this.finderService.findAll();
		Assert.isTrue(fs.contains(savedF));
		super.authenticate(null);
	}
	@Test
	public void testDeleteFinder() {
		Finder f, savedF;
		f = this.finderService.create();
		f.setMoment(new Date());
		savedF = this.finderService.save(f);
		this.finderService.delete(savedF);
		final Collection<Finder> fs = this.finderService.findAll();
		Assert.isTrue(!fs.contains(savedF));
	}

}
