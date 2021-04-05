package event.recommendation.system.services.user;

import event.recommendation.system.entities.user.User;
import event.recommendation.system.repositories.UserRepository;
import event.recommendation.system.services.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class UserProfileService {

    @Value("${upload.path}")
    private String uploadPath;
    private final UserRepository userRepository;
    private final FileService fileService;

    public void adaptAndSaveEditedUserProfile(MultipartFile avatar, String firstName,
                                              String lastName, String username, User user) {
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUsername(username);
        addUserAvatar(user, avatar);

        userRepository.save(user);
    }

    private void addUserAvatar(User user, MultipartFile avatar) {
        user.setAvatar(fileService.uploadFile(avatar));
    }

}
