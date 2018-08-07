package xyz.bzennn.vkmusicactualizer.models;

public interface AccountInfoInterface {
    String getUserId();
    String getUserToken();
    String getUserAvatarUrl();
    String getUserName();
    String getUserSurname();
    void requestUserInfoIntoPreferences();

}
