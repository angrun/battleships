package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class PostRow {


    @Column(name = "item_name")
    public String itemName;

    @Column(name = "quantity")
    @NotNull
    @Min(1)
    public Integer quantity;

    @Column(name = "price")
    @NotNull
    @Min(1)
    public Integer price;


    @Override
    public String toString() {
        return "OrderRow{" +
                "itemName='" + itemName + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }



}