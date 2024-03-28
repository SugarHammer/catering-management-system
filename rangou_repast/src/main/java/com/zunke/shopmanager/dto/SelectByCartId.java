package com.zunke.shopmanager.dto;

//select d.id, dishes_no, dishes_name, dishes_bulk, dishes_short_code, dishes_price, dishes_photo, dishes_action, subcategories_id, unit_id,
// cdc.id, cart_id, dishes_id, combo_id, dishes_number, combo_number from dishes d left join cart_dishes_combo cdc on d.id = cdc.dishes_id
public class SelectByCartId {
    private Integer id;
    private String dishesName;
    private Integer dishesNumber;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDishesName() {
        return dishesName;
    }

    public void setDishesName(String dishesName) {
        this.dishesName = dishesName;
    }

    public Integer getDishesNumber() {
        return dishesNumber;
    }

    public void setDishesNumber(Integer dishesNumber) {
        this.dishesNumber = dishesNumber;
    }
}
