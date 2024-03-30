package org.example.blog.Controllers;

import org.example.blog.Services.UserService;
import org.example.blog.payloads.ApiResponse;
import org.example.blog.payloads.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;
    @PostMapping("/")
    public ResponseEntity<UserDto> CreateUser(@RequestBody UserDto userDto){
        UserDto CreatedUserDto=this.userService.createUser(userDto);
        return new ResponseEntity<>(CreatedUserDto, HttpStatus.CREATED);

    }
@PutMapping("/{userId}")
    public ResponseEntity<UserDto> UpdateUser(@RequestBody UserDto userDto,@PathVariable("userId")Integer uid){

         UserDto updatedUser=this.userService.updateUser(userDto,uid);
         return ResponseEntity.ok(updatedUser);

}
@DeleteMapping("/{userId}")
public ResponseEntity<ApiResponse> DeleteUser(@PathVariable("userId")Integer uid){
 this.userService.deleteUser(uid);
 return  new ResponseEntity<ApiResponse>(new ApiResponse("User Deleted Successfully",true),HttpStatus.OK);

}

@GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        return ResponseEntity.ok(this.userService.getAllUsers());
}

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getSingleUser(@PathVariable("userId") Integer uId){

        return ResponseEntity.ok(this.userService.getUserById(uId));
    }





}
