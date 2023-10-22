package com.starpony.prohojemba.mappers;

import com.starpony.prohojemba.accounts.models.Account;
import com.starpony.prohojemba.accounts.repositories.mappers.AccountMapper;
import com.starpony.prohojemba.permissions.models.Permission;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;

import java.util.Collections;
import java.util.List;


@SpringBootTest
@SqlGroup({
        @Sql(scripts = {
            "/sql/insert_accounts_data.sql",
            "/sql/insert_permissions_data.sql",
            "/sql/insert_accountpermissions_data.sql"
        }, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
        @Sql(scripts = {
                "/sql/clear_accounts_data.sql",
                "/sql/clear_permissions_data.sql"
        }, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
})
public class AccountMapperTest {
    @Autowired
    private AccountMapper accountMapper;

    @Test
    public void selectById_idExist_returnAccount() {
        Account expectedResult = new Account(1, "admin@email.com",
                "$2a$04$k3imaBhoYjU4/K7rMCK4j.GykrXBhaNYejS6hNYyfeXFxGCBg8GP6",
                false, "admin", "/avatars/1.webp",
                List.of(
                   new Permission(1, "MANAGE_TITLES", "Manage Titles"),
                   new Permission(2, "MANAGE_TYPES", "Manage Types")
                ));

        Account result = accountMapper.selectById(1);

        Assertions.assertEquals(result, expectedResult);
    }

    @Test
    public void selectById_idNotExist_returnNull() {
        Account result = accountMapper.selectById(5);

        Assertions.assertNull(result);
    }

    @Test
    public void selectByEmail_emailExist_returnAccount() {
        Account expectedResult = new Account(2, "defaultuser@email.com",
                "$2a$04$k3imaBhoYjU4/K7rMCK4j.GykrXBhaNYejS6hNYyfeXFxGCBg8GP6",
                false, "default", null,
                List.of(
                        new Permission(2, "MANAGE_TYPES", "Manage Types")
                ));

        Account result = accountMapper.selectByEmail("defaultuser@email.com");

        Assertions.assertEquals(result, expectedResult);
    }

    @Test
    public void selectByEmail_emailNotExist_returnNull() {
        Account result = accountMapper.selectByEmail("test@mail.com");

        Assertions.assertNull(result);
    }

    @Test
    public void selectByUsername_usernameExist_returnAccount() {
        Account expectedResult = new Account(2, "defaultuser@email.com",
                "$2a$04$k3imaBhoYjU4/K7rMCK4j.GykrXBhaNYejS6hNYyfeXFxGCBg8GP6",
                false, "default", null,
                List.of(
                        new Permission(2, "MANAGE_TYPES", "Manage Types")
                ));

        Account result = accountMapper.selectByUsername("default");

        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    public void selectByUsername_usernameNotExist_returnNull() {
        Account result = accountMapper.selectByUsername("test");

        Assertions.assertNull(result);
    }

    @Test
    public void create_emailAndUsernameNotExist_correctCreate() {
        Account account = new Account(0, "test@email.com", "0000", false,
                "test", null, Collections.emptyList());

        accountMapper.create(account);

        Assertions.assertNotEquals(account.getId(), 0);
    }

    @Test
    public void create_emailExist_throwException() {
        Account account = new Account(0, "admin@email.com", "0000", false,
                "test", null, Collections.emptyList());

        Assertions.assertThrows(DuplicateKeyException.class, () -> accountMapper.create(account));
    }

    @Test
    public void create_emailNull_throwException() {
        Account account = new Account(0, null, "0000", false,
                "test", null, Collections.emptyList());

        Assertions.assertThrows(DataIntegrityViolationException.class, () -> accountMapper.create(account));
    }

    @Test
    public void create_usernameExist_throwException() {
        Account account = new Account(0, "test@email.com", "0000", false,
                "admin", null, Collections.emptyList());

        Assertions.assertThrows(DuplicateKeyException.class, () -> accountMapper.create(account));
    }

    @Test
    public void create_usernameNull_throwException() {
        Account account = new Account(0, "test@email.com", "0000", false,
                null, null, Collections.emptyList());

        Assertions.assertThrows(DataIntegrityViolationException.class, () -> accountMapper.create(account));
    }

    @Test
    public void update_emailNotExistUsernameExistInSameItem_correctUpdate() {
        Account account = new Account(2, "test@email.com", "0000", false,
                "default", null, List.of(new Permission(2, "MANAGE_TYPES", "Manage Types")));

        accountMapper.update(account);

        Account updatedAccount = accountMapper.selectById(2);

        Assertions.assertEquals(account, updatedAccount);
    }

    @Test
    public void update_emailExist_throwException() {
        Account account = new Account(2, "admin@email.com", "0000", false,
                "default", null, Collections.emptyList());

        Assertions.assertThrows(DuplicateKeyException.class, () -> accountMapper.update(account));
    }

    @Test
    public void update_emailNull_throwException() {
        Account account = new Account(2, null, "0000", false,
                "default", null, Collections.emptyList());

        Assertions.assertThrows(DataIntegrityViolationException.class, () -> accountMapper.update(account));
    }

    @Test
    public void update_usernameExist_throwException() {
        Account account = new Account(2, "test@email.com", "0000", false,
                "admin", null, Collections.emptyList());

        Assertions.assertThrows(DuplicateKeyException.class, () -> accountMapper.update(account));
    }

    @Test
    public void update_usernameNull_throwException() {
        Account account = new Account(2, "test@email.com", "0000", false,
                null, null, Collections.emptyList());

        Assertions.assertThrows(DataIntegrityViolationException.class, () -> accountMapper.update(account));
    }

    @Test
    public void delete_correctDelete() {
        accountMapper.delete(2);

        Account deletedAccount = accountMapper.selectById(2);

        Assertions.assertNull(deletedAccount);
    }
}
