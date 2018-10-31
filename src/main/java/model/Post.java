

package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    public Long id;

    public String orderNumber;

    @Valid
    public List<PostRow> orderRows;
}

