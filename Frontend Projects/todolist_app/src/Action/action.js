// actions.js

import { type } from "@testing-library/user-event/dist/type"


export const AddUserAction = (addedUser) =>(
  {
    type:'AddUser',
    payload:addedUser
  }
)


export const LoggedInUserAction = (loggedinUser) =>(
  {
    type:'loggedinuser',
    payload:loggedinUser
  }
)