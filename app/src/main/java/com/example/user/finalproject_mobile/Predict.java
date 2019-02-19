package com.example.user.finalproject_mobile;

import java.util.ArrayList;
import java.util.Random;

public class Predict {
    private int index,rand;
    private ArrayList<Restaurant> restaurantArrayList;
    private  Random random = new Random();

    public Predict() {
        restaurantArrayList = new ArrayList<>();
        Restaurant restaurant = new Restaurant();

        restaurant.setName("Oppa Daek Korean");
        restaurant.setLatitude(14.0659866);
        restaurant.setLongitude(100.6055605);
        restaurantArrayList.add(restaurant);

        restaurant = new Restaurant();
        restaurant.setName("สุกี้นายพัน");
        restaurant.setLatitude(14.0661861);
        restaurant.setLongitude(100.6055999);
        restaurantArrayList.add(restaurant);

        restaurant = new Restaurant();
        restaurant.setName("Namba Shabu ");
        restaurant.setLatitude(14.0658004);
        restaurant.setLongitude(100.6055583);
        restaurantArrayList.add(restaurant);

        restaurant = new Restaurant();
        restaurant.setName("ร้านข้าวมันไก่ป้ายแดง");
        restaurant.setLatitude(14.0661793);
        restaurant.setLongitude(100.6063242);
        restaurantArrayList.add(restaurant);

        restaurant = new Restaurant();
        restaurant.setName("SHINYA TOKYO BOWL");
        restaurant.setLatitude(14.0658363);
        restaurant.setLongitude(100.6061353);
        restaurantArrayList.add(restaurant);

        restaurant = new Restaurant();
        restaurant.setName("Shabu Laos");
        restaurant.setLatitude(14.0658363);
        restaurant.setLongitude(100.6061353);
        restaurantArrayList.add(restaurant);

        restaurant = new Restaurant();
        restaurant.setName("Shinkanzen Sushi ");
        restaurant.setLatitude(14.0658004);
        restaurant.setLongitude(100.6055583);
        restaurantArrayList.add(restaurant);

        restaurant = new Restaurant();
        restaurant.setName("คุณเบิร์ด ข้าวเหนียว หมูทอด ไก่ทอด");
        restaurant.setLatitude(14.0659546);
        restaurant.setLongitude(100.6071965);
        restaurantArrayList.add(restaurant);

        restaurant = new Restaurant();
        restaurant.setName("บุญเพ็ง เมี่ยง ปลาเผา ");
        restaurant.setLatitude(14.065925);
        restaurant.setLongitude(100.6074315);
        restaurantArrayList.add(restaurant);

        restaurant = new Restaurant();
        restaurant.setName("SteakHolder");
        restaurant.setLatitude(14.0659073);
        restaurant.setLongitude(100.6076017);
        restaurantArrayList.add(restaurant);

        restaurant = new Restaurant();
        restaurant.setName("นายป้อม Super เล้ง");
        restaurant.setLatitude(14.0659073);
        restaurant.setLongitude(100.6076017);
        restaurantArrayList.add(restaurant);

        restaurant = new Restaurant();
        restaurant.setName("ย่างเนย เชียงราก2");
        restaurant.setLatitude(14.0656751);
        restaurant.setLongitude(100.6096809);
        restaurantArrayList.add(restaurant);

        restaurant = new Restaurant();
        restaurant.setName("เตี๋ยว ม. ต้มยำ@TU");
        restaurant.setLatitude(14.0656145);
        restaurant.setLongitude(100.611348);
        restaurantArrayList.add(restaurant);

        restaurant = new Restaurant();
        restaurant.setName("เสวย");
        restaurant.setLatitude(14.0658603);
        restaurant.setLongitude(100.6078306);
        restaurantArrayList.add(restaurant);

        restaurant = new Restaurant();
        restaurant.setName("ข้าวหน้าเป็ด ");
        restaurant.setLatitude(14.065613);
        restaurant.setLongitude(100.6079594);
        restaurantArrayList.add(restaurant);

        restaurant = new Restaurant();
        restaurant.setName("Exclusive Nana");
        restaurant.setLatitude(14.066153);
        restaurant.setLongitude(100.6091755);
        restaurantArrayList.add(restaurant);

        restaurant = new Restaurant();
        restaurant.setName("นายเกรียงอาหารอีสาน");
        restaurant.setLatitude(14.0662372);
        restaurant.setLongitude(100.6086413);
        restaurantArrayList.add(restaurant);

    }
    public int getIndex(){
        rand=restaurantArrayList.size()-1;
        index = random.nextInt(rand);
        return index;
    }

    public ArrayList<Restaurant> getRestaurantArrayList() {
        return restaurantArrayList;
    }

}
