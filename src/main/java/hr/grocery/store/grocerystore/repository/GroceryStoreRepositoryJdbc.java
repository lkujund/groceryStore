//package hr.grocery.store.grocerystore.repository;
//
//import hr.grocery.store.grocerystore.model.Grocery;
//import hr.grocery.store.grocerystore.model.GroceryCategory;
//import hr.grocery.store.grocerystore.model.GrocerySearchForm;
//import hr.grocery.store.grocerystore.model.MeasuringUnit;
//import org.springframework.context.annotation.Primary;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.RowMapper;
//import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
//import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
//import org.springframework.stereotype.Repository;
//
//import javax.sql.DataSource;
//import java.sql.Blob;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//
//@Repository
//@Primary
//public class GroceryStoreRepositoryJdbc implements GroceryStoreRepository{
//
//    private final JdbcTemplate jdbcTemplate;
//
//    private final SimpleJdbcInsert simpleJdbcInsert;
//
//    public GroceryStoreRepositoryJdbc(JdbcTemplate jdbcTemplate, DataSource dataSource)
//    {
//        this.jdbcTemplate = jdbcTemplate;
//        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource);
//        simpleJdbcInsert.withTableName("GROCERY")
//                .usingGeneratedKeyColumns("ID");
//    }
//    @Override
//    public List<Grocery> findAll() {
//
//        return jdbcTemplate.query("SELECT * FROM GROCERY", new GroceryRowMapper());
//
//
//    }
//
//    @Override
//    public Optional<Grocery> findById(Integer id) {
//        Grocery grocery =
//            jdbcTemplate.queryForObject("SELECT * FROM GROCERY WHERE ID = ?",
//                new GroceryRowMapper(), id);
//
//        if (Optional.ofNullable(grocery).isPresent())
//        {
//            return Optional.of(grocery);
//        } else
//        {
//            return Optional.empty();
//        }
//
//    }
//
//    @Override
//    public void save(Grocery grocery) {
//
//        MapSqlParameterSource params = new MapSqlParameterSource();
//        params.addValue("name", grocery.getName());
//        params.addValue("category_id", grocery.getCategory().ordinal() + 1);
//        params.addValue("measuring_unit_id", grocery.getMeasuringUnit().ordinal() + 1);
//        params.addValue("measure", grocery.getMeasure());
//        params.addValue("price", grocery.getPrice());
//        params.addValue("description", grocery.getDescription());
//        params.addValue("image", grocery.getImage()); //PRETVORITI U BLOB
//
//        simpleJdbcInsert.executeAndReturnKey(params);
//    }
//
//    @Override
//    public List<Grocery> filterByCriteria(GrocerySearchForm grocerySearchForm) {
//        StringBuilder sqlQuery = new StringBuilder("SELECT * FROM GROCERY WHERE 1 = 1 ");
//
//        List<String> queryArgs = new ArrayList<>();
//
//        if(!grocerySearchForm.getName().isEmpty()){
//            sqlQuery.append("AND NAME LIKE ('%'||?||'%') ");
//            queryArgs.add(grocerySearchForm.getName());
//        }
//
//        if(!grocerySearchForm.getCategory().isEmpty()){
//            sqlQuery.append("AND CATEGORY_ID = ? ");
//            queryArgs.add(String.valueOf(GroceryCategory
//                    .valueOf(grocerySearchForm.getCategory()).ordinal() + 1));
//        }
//
//        if(Optional.ofNullable(grocerySearchForm.getLowerPrice()).isPresent()){
//            sqlQuery.append("AND PRICE >= ? ");
//            queryArgs.add(grocerySearchForm.getLowerPrice().toString());
//        }
//
//        if(Optional.ofNullable(grocerySearchForm.getUpperPrice()).isPresent()){
//            sqlQuery.append("AND PRICE <= ? ");
//            queryArgs.add(grocerySearchForm.getUpperPrice().toString());
//        }
//
//        if(!grocerySearchForm.getDescription().isEmpty()){
//            sqlQuery.append("AND DESCRIPTION LIKE ('%'||?||'%') ");
//            queryArgs.add(grocerySearchForm.getDescription());
//        }
//
//        Object[] preparedStatementArgs = new Object[queryArgs.size()];
//        for(int i = 0; i < preparedStatementArgs.length; i++){
//            preparedStatementArgs[i] = queryArgs.get(i);
//        }
//
//        return this.jdbcTemplate
//                        .query(sqlQuery.toString(), new GroceryRowMapper(), preparedStatementArgs);
//
//
//
//    }
//
//    private class GroceryRowMapper implements RowMapper<Grocery>{
//
//        @Override
//        public Grocery mapRow(ResultSet rs, int rowNum) throws SQLException {
//            Grocery newGrocery = new Grocery();
//            newGrocery.setId(rs.getInt("ID"));
//            newGrocery.setName(rs.getString("NAME"));
//
//            Integer categoryId = rs.getInt("CATEGORY_ID");
//
//            Optional<GroceryCategory> groceryCategoryOptional =
//                    Arrays.stream(GroceryCategory.values())
//                    .filter(gc -> gc.ordinal() == categoryId - 1)
//                            .findFirst();
//
//            groceryCategoryOptional.ifPresent(newGrocery::setCategory);
//
//
//            Integer measuringUnitId = rs.getInt("MEASURING_UNIT_ID");
//
//            Optional<MeasuringUnit> measuringUnitOptional =
//                    Arrays.stream(MeasuringUnit.values())
//                            .filter(mu -> mu.ordinal() == measuringUnitId - 1)
//                            .findFirst();
//
//            measuringUnitOptional.ifPresent(newGrocery::setMeasuringUnit);
//
//            newGrocery.setMeasure(rs.getBigDecimal("MEASURE"));
//            newGrocery.setPrice(rs.getBigDecimal("PRICE"));
//            newGrocery.setDescription(rs.getString("DESCRIPTION"));
//
//
//            newGrocery.setImage(mapBlobToByteArray(rs.getBlob("IMAGE")));
//
//            return newGrocery;
//        }
//
//        private byte[] mapBlobToByteArray(Blob image) {
//            byte[] imageArr;
//            if (image == null){
//                return new byte[]{0};
//            }
//            try {
//                int length = (int) image.length();
//                imageArr = image.getBytes(1, length);
//            } catch (SQLException e) {
//                return new byte[]{0};
//            }
//            return  imageArr;
//        }
//    }
//}
