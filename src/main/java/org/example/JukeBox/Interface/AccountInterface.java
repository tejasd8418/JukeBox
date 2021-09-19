package org.example.JukeBox.Interface;

public interface AccountInterface {
    public boolean checkIfUserHaveAccount(int userId, String password);

    public void createAccount(String name, int age, String email, String password);

    public int getUserId(String email, String password);
}
