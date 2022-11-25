package pendency.driver;

import pendency.service.PendencySystem;

import java.util.Arrays;

public class MainClass {

    public static void main(String[] args) {
        PendencySystem pendencySystem = new PendencySystem();
        pendencySystem.startTracking("1112", Arrays.asList("UPI", "Karnataka", "Bangalore"));
        pendencySystem.startTracking("2451", Arrays.asList("UPI", "Karnataka", "Mysore"));
        pendencySystem.startTracking("3421", Arrays.asList("UPI", "Rajasthan", "Jaipur"));
        pendencySystem.startTracking("1221", Arrays.asList("Wallet", "Karnataka", "Bangalore"));

        System.out.println("[UPI] get count: " + pendencySystem.getCounts(Arrays.asList("UPI")));
        System.out.println("[UPI, Karnataka] get count: " + pendencySystem.getCounts(Arrays.asList("UPI", "Karnataka")));
        System.out.println("[UPI, Karnataka, Bangalore] get count: " + pendencySystem.getCounts(Arrays.asList("UPI", "Karnataka", "Bangalore")));

        System.out.println("[Bangalore] get count: " + pendencySystem.getCounts(Arrays.asList("Bangalore")));

        pendencySystem.startTracking("4221", Arrays.asList("Wallet", "Karnataka", "Bangalore"));
        pendencySystem.stopTracking("1112");
        pendencySystem.stopTracking("2451");

        System.out.println("[UPI] get count: " + pendencySystem.getCounts(Arrays.asList("UPI")));
        System.out.println("[Wallet] get count: " + pendencySystem.getCounts(Arrays.asList("Wallet")));
        System.out.println("[UPI, Karnataka, Bangalore] get count: " + pendencySystem.getCounts(Arrays.asList("UPI", "Karnataka", "Bangalore")));
    }
}
