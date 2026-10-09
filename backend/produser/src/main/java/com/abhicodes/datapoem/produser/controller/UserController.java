package com.abhicodes.datapoem.produser.controller;

import com.abhicodes.datapoem.produser.entity.Cart;
import com.abhicodes.datapoem.produser.entity.User;
import com.abhicodes.datapoem.produser.exception.ResourceNotFoundException;
import com.abhicodes.datapoem.produser.payloads.ApiResponse;
import com.abhicodes.datapoem.produser.payloads.UserProfile;
import com.abhicodes.datapoem.produser.payloads.UserSummary;
import com.abhicodes.datapoem.produser.repository.CartRepository;
import com.abhicodes.datapoem.produser.repository.UserRepository;
import com.abhicodes.datapoem.produser.security.CurrentUser;
import com.abhicodes.datapoem.produser.security.UserPrincipal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;

//    @Autowired
//    private VoteRepository voteRepository;
//
//    @Autowired
//    private PollService pollService;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
    public UserSummary getCurrentUser(@CurrentUser UserPrincipal currentUser) {
        System.out.println("coming here");
        UserSummary userSummary = new UserSummary(currentUser.getId(), currentUser.getUsername(), currentUser.getName());
        return userSummary;
    }

//    @GetMapping("/user/checkUsernameAvailability")
//    public UserIdentityAvailability checkUsernameAvailability(@RequestParam(value = "username") String username) {
//        Boolean isAvailable = !userRepository.existsByUsername(username);
//        return new UserIdentityAvailability(isAvailable);
//    }
//
//    @GetMapping("/user/checkEmailAvailability")
//    public UserIdentityAvailability checkEmailAvailability(@RequestParam(value = "email") String email) {
//        Boolean isAvailable = !userRepository.existsByEmail(email);
//        return new UserIdentityAvailability(isAvailable);
//    }

    @GetMapping("/users/{username}")
    public UserProfile getUserProfile(@PathVariable(value = "username") String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));
        //System.out.println("username: "+user.getUsername());
//        long pollCount = pollRepository.countByCreatedBy(user.getId());
//        long voteCount = voteRepository.countByUserId(user.getId());
          Cart cart = user.getCart();

        UserProfile userProfile = new UserProfile(user.getId(), user.getUsername(), user.getName(), cart.getWallet_balance());

        return userProfile;
    }

    @PostMapping("/users/{username}/addMoney")
    public ResponseEntity<?> addMoney(@PathVariable(value = "username") String username, @RequestBody String amount){
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));
        Cart cart = user.getCart();

        System.out.println("Amount: "+amount);
        String str = amount.replace("\"","");
        System.out.println("cartid: "+cart.getId());

        System.out.println("before update:"+cart.getWallet_balance());
        long temp = 0;
        if(cart.getWallet_balance()>0){
            temp = temp + cart.getWallet_balance()+Long.parseLong(str);
        }
        else{
            temp=cart.getWallet_balance();
        }
        cart.setWallet_balance(temp);
        System.out.println("after update: "+cart.getWallet_balance());
        cartRepository.save(cart);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/users/{username}")
                .buildAndExpand(user.getUsername()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "Amount added "));
    }
//    @GetMapping("/users/{username}/polls")
//    public PagedResponse<PollResponse> getPollsCreatedBy(@PathVariable(value = "username") String username,
//                                                         @CurrentUser UserPrincipal currentUser,
//                                                         @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
//                                                         @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {
//        return pollService.getPollsCreatedBy(username, currentUser, page, size);
//    }
//
//    @GetMapping("/users/{username}/votes")
//    public PagedResponse<PollResponse> getPollsVotedBy(@PathVariable(value = "username") String username,
//                                                       @CurrentUser UserPrincipal currentUser,
//                                                       @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
//                                                       @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {
//        return pollService.getPollsVotedBy(username, currentUser, page, size);
//    }
//    @PostMapping("/users/{username}/buy")
//    public ResponseEntity<?> buyProduct(@PathVariable(value ="username") String username, @RequestBody )
}
