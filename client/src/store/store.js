import { configureStore } from "@reduxjs/toolkit";
import dataReducer from '../slicer/main';

export const store = configureStore({
    reducer: {
        data: dataReducer,
    },
});
