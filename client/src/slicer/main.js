
import { createSlice, createAsyncThunk } from '@reduxjs/toolkit';
import { getApi, postApi } from '../api/api.js';


    
export const fetchData = createAsyncThunk('data/fetchData', async (path) => {
    return await getApi(path);
    });

export const postData = createAsyncThunk('data/postData', async ({ path, data }) => {
        return await postApi(path, data);
    });

export const fetchUserById = createAsyncThunk('data/fetchUserById', async (userId) => {
    const response = await getApi(`user/${userId}`); 
    console.log('Response:', response);
    return { userId, user: response };
    });
    
export const dataSlice = createSlice({
        name: 'data',
        initialState: {
        items: [],
        users: [],
        status: 'idle',
        },
        extraReducers: (builder) => {
        builder
            .addCase(fetchUserById.fulfilled, (state, action) => {
            state.users[action.payload.userId] = action.payload.user; // 사용자 ID를 키로 사용하여 사용자 정보 저장
            })
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



