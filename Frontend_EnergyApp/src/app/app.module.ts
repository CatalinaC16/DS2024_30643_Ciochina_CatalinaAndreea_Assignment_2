import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {HttpClientModule} from "@angular/common/http";
import {AuthGuard} from "./guards/AuthGuard.guard";
import {FormsModule} from "@angular/forms";
import {AuthComponent} from "./components/auth/Auth.component";
import {AdminGuard} from "./guards/AdminGuard.guard";
import {HomeComponent} from "./components/home-page/Home.component";
import {ProfileComponent} from "./components/profile-page/Profile.component";
import {MyDevicesComponent} from "./components/user-devices-page/MyDevices.component";
import {NotAuthComponent} from "./components/not-auth-page/NotAuth.component";
import {AdminUsersComponent} from "./components/admin-users-page/AdminUsers.component";
import {AdminDevicesComponent} from "./components/admin-devices-page/AdminDevices.component";

@NgModule({
  declarations: [
    AppComponent,
    AuthComponent,
    HomeComponent,
    ProfileComponent,
    MyDevicesComponent,
    NotAuthComponent,
    AdminUsersComponent,
    AdminDevicesComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
  ],
  providers: [AuthGuard,AdminGuard],
  bootstrap: [AppComponent]
})
export class AppModule {
}
