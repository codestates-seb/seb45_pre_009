
import { createSlice, createAsyncThunk } from '@reduxjs/toolkit';
import { getApi } from '../api/api.js';


    
export const fetchData = createAsyncThunk('data/fetchData', async (path) => {
    return await getApi(path);
    });
    
export const dataSlice = createSlice({
        name: 'data',
        initialState: {
        items: [],
        status: 'idle',
        },
        extraReducers: (builder) => {
        builder
            .addCase(fetchData.pending, (state) => {
            state.status = 'loading';
            })
            .addCase(fetchData.fulfilled, (state, action) => {
            state.status = 'succeeded';
            state.items = action.payload;
            })
            .addCase(fetchData.rejected, (state) => {
            state.status = 'failed';
            });
        },
    });

    
    export default dataSlice.reducer;



