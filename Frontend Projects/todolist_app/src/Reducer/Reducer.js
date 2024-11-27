// reducer.js

const initialState = {
  count: 0,
  user:{
    lists:[]
  },
  loggedinUser:{}
};

const counterReducer = (state = initialState, action) => {
  switch (action.type) {
    
    case 'AddUser':
      return { ...state, lists: [...state.user, action.payload]  };

     case 'loggedinuser':
      return{ ...state, loggedinUser:action.payload} 
    default:
      return state;
  }
};

export default counterReducer;
