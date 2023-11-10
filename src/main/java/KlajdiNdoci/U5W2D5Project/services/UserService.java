package KlajdiNdoci.U5W2D5Project.services;

import KlajdiNdoci.U5W2D5Project.entities.User;
import KlajdiNdoci.U5W2D5Project.exceptions.NotFoundException;
import KlajdiNdoci.U5W2D5Project.payloads.NewUserDTO;
import KlajdiNdoci.U5W2D5Project.repositories.UserRepository;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Cloudinary cloudinary;


    public Page<User> getUtenti(int page, int size, String orderBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));
        return userRepository.findAll(pageable);
    }

    public User save(NewUserDTO body) throws IOException {
        User newUser = new User();
        newUser.setAvatar("https://ui-avatars.com/api/?name=" + body.name() + "+" + body.surname());
        newUser.setName(body.name());
        newUser.setSurname(body.surname());
        newUser.setEmail(body.email());
        return userRepository.save(newUser);
    }

    public User findById(long id) throws NotFoundException{
        User found = null;
        for (User user : userRepository.findAll()) {
            if (user.getId() == id) {
                found = user;
            }
        }
        if (found == null) {
            throw new NotFoundException(id);
        } else {
            return found;
        }
    }

    public void findByIdAndDelete(int id)throws NotFoundException {
        User found = this.findById(id);
        userRepository.delete(found);
    }

    public User findByIdAndUpdate(int id, User body) throws NotFoundException{
        User found = null;

        for (User user : userRepository.findAll()) {
            if (user.getId() == id) {
                found = user;
                found.setName(body.getName());
                found.setSurname(body.getSurname());
                found.setAvatar("https://ui-avatars.com/api/?name=" + body.getName() + "+" + body.getSurname());
                found.setEmail(body.getEmail());
            }
        }
        if (found == null) {
            throw new NotFoundException(id);
        } else {
            return userRepository.save(found);
        }
    }
    public User uploadPicture(MultipartFile file, long id) throws IOException {
        User user = userRepository.findById(id).orElseThrow(()-> new NotFoundException(id));
        String url = (String) cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap()).get("url");
        user.setAvatar(url);
        userRepository.save(user);
        return user;
    }
}
