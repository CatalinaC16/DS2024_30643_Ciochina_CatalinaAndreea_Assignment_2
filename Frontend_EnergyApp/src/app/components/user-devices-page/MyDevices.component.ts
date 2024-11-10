import {Component, OnInit} from '@angular/core';
import {AuthService} from "../../services/Auth.service";
import {Router} from "@angular/router";
import {UserDto} from "../../dtos/UserDto";
import {UserService} from "../../services/User.service";
import {Role} from "../../dtos/Role";
import {DeviceDto} from "../../dtos/DeviceDto";
import {DeviceService} from "../../services/Device.service";

@Component({
  selector: 'app-my-devices',
  templateUrl: './MyDevices.component.html',
  styleUrls: ['./MyDevices.component.css']
})
export class MyDevicesComponent implements OnInit {
  devices: DeviceDto[] = [];
  errorMessage = '';
  id = '';
  role!: Role;

  constructor(private deviceService: DeviceService,
              private userService: UserService,
              private router: Router,
              private authService: AuthService) {
  }

  ngOnInit(): void {
    const token = this.authService.getToken();
    let userEmail: string;
    if (token) {
      const payload = JSON.parse(atob(token.split('.')[1]));
      userEmail = payload.sub;
      this.userService.getUserByEmail(userEmail).subscribe(
        (response: UserDto) => {
          this.id = response.id;
          this.role = response.role;
          if (this.id != '') {
            this.deviceService.getDevicesByUserId(this.id).subscribe(
              (data) => {
                this.devices = data;
              },
              (error) => {
                this.errorMessage = 'Could not load devices. Please try again later.';
                console.error('Error fetching devices:', error);
              }
            );
          }
        },
        (error) => {
          this.errorMessage = 'Failed to load user';
          console.error(error);
        }
      );
    }
  }

  logout(): void {
    this.authService.logout();
    this.router.navigate(["/login"]).then();
  }

  viewUserDetails() {
    this.router.navigate(["/myProfile"]).then();
  }

  goToHomePage() {
    this.router.navigate(["/home"]).then();
  }
}
