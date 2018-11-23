package Spring5MvcRestApplication.v1.mapper;

import Spring5MvcRestApplication.v1.model.CategoryDTO;
import Spring5MvcRestApplication.domain.Category;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CategoryMapperTest {

    public static final String NAME = "Joe";
    public static final long ID = 1L;
    CategoryMapper categoryMapper = CategoryMapper.INSTANCE;

    @Test
    public void categoryToCategoryDTO() throws  Exception{

        // given
        Category category = new Category();
        category.setName(NAME);
        category.setId(ID);

        // when
        CategoryDTO categoryDTO = categoryMapper.categoryToCatergoryDTO(category);

        // then

        assertEquals(Long.valueOf(ID), categoryDTO.getId());
        assertEquals(NAME, categoryDTO.getName());
    }
}
