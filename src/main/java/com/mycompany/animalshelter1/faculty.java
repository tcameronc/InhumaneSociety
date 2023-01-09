/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.animalshelter1;


public class faculty {
    private int number;
    private String sex;
    private String species;
    private String breed;
    private int age;
    private String name;
    private int temper;
    private int adoptable;
    private int username;

    

    faculty(int number, String sex, String species, String breed, int age, String name, int temper, int adoptable, int username) {

        this.number = number;
        this.sex = sex;
        this.species = species;
        this.breed = breed;
        this.age = age;
        this.name = name;
        this.temper = temper;
        this.adoptable = adoptable;
        this.username = username;
    }

   
    public int getNumber() {
        return number;
    }

   
    public void setNumber(int number) {
        this.number = number;
    }

   
    public String getSex() {
        return sex;
    }


    public void setSex(String sex) {
        this.sex = sex;
    }


    public String getSpecies() {
        return species;
    }

   
    public void setSpecies(String species) {
        this.species = species;
    }

    public String getBreed() {
        return breed;
    }

   
    public void setBreed(String breed) {
        this.breed = breed;
    }
   
    public int getAge() {
        return age;
    }


    public void setAge(int age) {
        this.age = age;
    }
    
    public String getName() {
        return name;
    }

   
    public void setName(String name) {
        this.name = name;
    }
    
        public int getTemper() {
        return temper;
    }


    public void setTemper(int temper) {
        this.temper = temper;
    }
        public int getAdoptable() {
        return adoptable;
    }


    public void setAdoptable(int adoptable) {
        this.adoptable = adoptable;
    }
    public int getUsername() {
        return username;
    }


    public void setUsername(int username) {
        this.username = username;
    }

}
 
