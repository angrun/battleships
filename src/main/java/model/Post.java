

package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.Valid;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_seq")
    @SequenceGenerator(name = "my_seq", sequenceName = "order_sequence", allocationSize = 1)
    public Long id;

    @Column(name = "order_number")
    public String orderNumber;

    @Valid
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "order_rows",
            joinColumns = @JoinColumn(name = "orders_id", referencedColumnName = "id"))
//Nii kui kusitakse isikut - tõmba talle telefoni kohe ka juurde
    public List<PostRow> orderRows;
}

