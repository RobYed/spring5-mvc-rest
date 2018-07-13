package guru.springfamework.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Customer {

    private static final String BASE_URL = "/api/v1/customers/"; // TODO: get from spring config

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;

    @Setter(AccessLevel.NONE)
    private String customerUrl;

    public String getCustomerUrl() {
        return BASE_URL + id;
    }
}
