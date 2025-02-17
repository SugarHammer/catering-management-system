export default {
    //菜品大类表
    categoriesSelectAll:"/api/categories/selectAll",
    categoriesDeleteById:'/api/categories/deleteById',
    categoriesInsert:'/api/categories/insert',
    categoriesUpdateById:'/api/categories/updateById',
    //菜品小类
    subcategoriesSelectAll:"/api/subcategories/selectAll",
    subcategoriesInsert:'/api/subcategories/insert',
    subcategoriesDeleteById:'/api/subcategories/deleteById',
    subcategoriesUpdateById:'/api/subcategories/updateById',
    //菜品信息
    dishesSelectById:"/api/dishes/selectById",
    uploadDishesPhoto:'/api/upload/dishesPhoto',
    dishesSelectAll:"/api/dishes/selectAll",
    subcategoriesSelectIdAndName:"/api/subcategories/selectIdAndName",
    dishesInsert:'/api/dishes/insert',
    dishesDeleteById:'/api/dishes/deleteById',
    dishesUpdateById:'/api/dishes/updateById',
    downloadDishesExcel:"/api/download/dishesExcel",
    downloadDishesTemplate:'/api/download/dishesTemplate',
    uploadDishesExcel:"/api/upload/dishesExcel",
    //菜品单位
    unitSelectAll:"/api/unit/selectAll",
    unitInsert:'/api/unit/insert',
    unitDeleteById:'/api/unit/deleteById',
    unitUpdateById:'/api/unit/updateById',
    //套餐管理
    comboSelectAll:"/api/combo/selectAll",
    comboInsert:'/api/combo/insert',
    comboDeleteById:'/api/combo/deleteById',
    comboUpdateById:'/api/combo/updateById',
    dishesSelectByCombo:"/api/dishes/selectByCombo",
    dishesSelectByNotCombo:"/api/dishes/selectByNotCombo",
    dishesComboInsert:'/api/dishesCombo/insert',
    dishesComboDeleteByDishesIdAndComboId:'/api/dishesCombo/deleteByDishesIdAndComboId',
    dishesComboUpdateNumberByDishesIdAndComboId:'/api/dishesCombo/updateNumberByDishesIdAndComboId',
    uploadDishesComboPhoto:"/api/upload/dishesComboPhoto",
    //做法表
    recipeSelectAll:"/api/recipe/selectAll",
    recipeInsert:'/api/recipe/insert',
    recipeUpdateById:'/api/recipe/updateById',
    recipeDeleteById:'/api/recipe/deleteById',
    //做法关联表
    recipeSelectByDishesId:"/api/recipe/selectByDishesId",
    dishesRecipeInsert:'/api/dishesRecipe/insert',
    dishesRecipeDeleteByDishesIdAndRecipeId:'/api/dishesRecipe/deleteByDishesIdAndRecipeId'
}