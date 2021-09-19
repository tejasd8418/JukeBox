package org.example.HelloMaven.DaoTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
//import static org.junit.jupiter.api.Assertions.assertTrue;


import org.example.JukeBox.DaoClass.AccountImpl;

public class AccountImplTest {

    AccountImpl accountImpl;

    @BeforeEach
    void setUp(){
        accountImpl = new AccountImpl();

    }

    @AfterEach
    void tearDown(){
        accountImpl = null;
    }

    @Test
    public void givenEmailAndPasswordReturnUserId(){
        int userIdTest1 = accountImpl.getUserId("tejasd8418@gmail.com","123456");
        int userIdTest2 = accountImpl.getUserId("sachin@gmail.com","sachin");
        int userIdTest3 = accountImpl.getUserId("abcd@gmail.com","abcd");
        assertEquals(1001,userIdTest1);
        assertEquals(1004,userIdTest2);
        assertNotEquals(1003,userIdTest3);
        assertNotEquals(1004,userIdTest1);
    }

    @Test
    public void givenUserIdAndPasswordCheckIfUserHaveAccount(){
        boolean check1 = accountImpl.checkIfUserHaveAccount(1001,"123456");
        boolean check2 = accountImpl.checkIfUserHaveAccount(1003,"Rishabh");
        boolean check3 = accountImpl.checkIfUserHaveAccount(1004,"ABCDefg");
        boolean check4 = accountImpl.checkIfUserHaveAccount(100156,"asdqwe");
        assertTrue(check1);
        assertTrue(check2);
        assertFalse(check3);
        assertFalse(check4);

    }
}
