import { CanActivateFn, Router } from '@angular/router';
import { TokenService } from '../services/token.service';
import { AccountService } from '../services/account.service';
import { inject } from '@angular/core';


export const authGuard: CanActivateFn = (route, state) => {
const tokenService = new TokenService();
const router = inject(Router);
const accountService = new AccountService(tokenService);
  if(!tokenService.loggedIn())
  {
    tokenService.remove();
    accountService.changeStatus(false);
    router.navigateByUrl("/login");
    return false;
  }
  return true;
};
