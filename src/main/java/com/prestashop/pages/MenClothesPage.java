package com.prestashop.pages;

public class MenClothesPage extends ClothesPage {

    public ProductCardPage getFirstSampleOfClothes() {
        productsFromThePage.get(0).click();
        return new ProductCardPage();
    }

}
