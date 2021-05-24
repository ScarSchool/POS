package janssen.spring.services;

import janssen.spring.entities.Sales;
import janssen.spring.repositories.SalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.NoSuchElementException;

@Service
public class SalesService {

  protected final SalesRepository data;

  public SalesService(@Autowired SalesRepository data) {
    this.data = data;
  }

  public Collection<Sales> listAll() {
    return data.findAll();
  }

  public Sales create(Sales sales) {
    verifySalesIsUnique(sales);
    sales = data.saveAndFlush(sales);
    return sales;
  }

  public Sales get(long id) {
    verifySalesExists(id);
    return data.findById(id).get();
  }

  public Sales update(long id, Sales sales) {
    sales.setId(id);
    verifySalesExists(id);
    return data.saveAndFlush(sales);
  }

  public void delete(long id) {
    try { data.deleteById(id); }
    catch (EmptyResultDataAccessException ignored) {}
  }

  protected void verifySalesExists(long id) {
    if (!data.existsById(id))
      throw new NoSuchElementException("Sales with id '" + id + "' does not exist");
  }

  protected void verifySalesIsUnique(Sales sales) {
    if (data.existsById(sales.getId())) {
      throw new DuplicateKeyException("Sales with id '" + sales.getId() + "' already exists");
    }
  }

}
