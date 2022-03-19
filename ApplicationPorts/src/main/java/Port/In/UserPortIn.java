package Port.In;

import com.example.kregielniaspring.exceptions.LoginInUseException;
import com.example.kregielniaspring.model.User;

import java.util.UUID;

public interface UserPortIn {

    User create(User user) throws LoginInUseException;

    User delete(UUID uuid);

    User update(User object) throws LoginInUseException;
}