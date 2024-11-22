// reducer.js

const initialState = {
  count: 0,
  users:[],
  loggedinUser:{}
};

const counterReducer = (state = initialState, action) => {
  switch (action.type) {
    
    case 'AddUser':
      return { ...state, users: [...state.users, action.payload]  };

     case 'loggedinuser':
      return{ ...state, loggedinUser:action.payload} 
    default:
      return state;
  }
};

export default counterReducer;
