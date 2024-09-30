package com.example.foodplanner.mealofday.apphome.model;

import java.io.Serializable;

public class Meal implements Serializable {
    private String strMeal;
    private String strArea;
    private String strInstructions;
    private String strMealThumb;
    private String strYoutube;

    public String idIngredient;
    public String strIngredient;
    public String strDescription;
    public Object strType;
    public String strIngredientThumb;

    public String getStrIngredientThumb() {
        return strIngredientThumb;
    }

    public void setStrIngredientThumb(String strIngredientThumb) {
        this.strIngredientThumb = strIngredientThumb;
    }

    public String getStrIngredient() {
        return strIngredient;
    }

    public void setStrIngredient(String strIngredient) {
        this.strIngredient = strIngredient;
    }

    public String getIdIngredient() {
        return idIngredient;
    }

    public void setIdIngredient(String idIngredient) {
        this.idIngredient = idIngredient;
    }

    public String getStrDescription() {
        return strDescription;
    }

    public void setStrDescription(String strDescription) {
        this.strDescription = strDescription;
    }

    public Object getStrType() {
        return strType;
    }

    public void setStrType(Object strType) {
        this.strType = strType;
    }




//    private static final long serialVersionUID = 1L;

    public String getFlagUrl() {
        String countryCode = getCountryCode(this.strArea);
        return "https://flagcdn.com/160x120/" + countryCode + ".png";
    }

    public String getIngredientUrl(){
        return "https://www.themealdb.com/images/ingredients/" + strIngredient +".png";
    }

    private String getCountryCode(String countryName) {
        // Map country names to their codes
        switch (countryName.toLowerCase()) {
            case "american":
                return "us";
            case "british":
                return "gb";
            case "canadian":
                return "ca";
            case "chinese":
                return "cn";
            case "croatian":
                return "hr";
            case "dutch":
                return "nl";
            case "egyptian":
                return "eg";
            case "filipino":
                return "ph";
            case "french":
                return "fr";
            case "greek":
                return "gr";
            case "indian":
                return "in";
            case "irish":
                return "ie";
            case "italian":
                return "it";
            case "jamaican":
                return "jm";
            case "japanese":
                return "jp";
            case "kenyan":
                return "ke";
            case "malaysian":
                return "my";
            case "mexican":
                return "mx";
            case "moroccan":
                return "ma";
            case "polish":
                return "pl";
            case "portuguese":
                return "pt";
            case "russian":
                return "ru";
            case "spanish":
                return "es";
            case "thai":
                return "th";
            case "tunisian":
                return "tn";
            case "turkish":
                return "tr";
            case "ukrainian":
                return "ua";
            case "unknown":
                return ""; // For unknown, we can return an empty string or a default value
            case "vietnamese":
                return "vn";
            default:
                return ""; // Default case for unmapped countries
        }
    }

    public String getStrMeal() {
        return strMeal;
    }

    public void setStrMeal(String strMeal) {
        this.strMeal = strMeal;
    }

    public String getStrArea() {
        return strArea;
    }

    public void setStrArea(String strArea) {
        this.strArea = strArea;
    }

    public String getStrInstructions() {
        return strInstructions;
    }

    public void setStrInstructions(String strInstructions) {
        this.strInstructions = strInstructions;
    }

    public String getStrMealThumb() {
        return strMealThumb;
    }

    public void setStrMealThumb(String strMealThumb) {
        this.strMealThumb = strMealThumb;
    }

    public String getStrYoutube() {
        return strYoutube;
    }

    public void setStrYoutube(String strYoutube) {
        this.strYoutube = strYoutube;
    }


}
