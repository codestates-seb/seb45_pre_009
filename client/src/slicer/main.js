
import { createSlice, createAsyncThunk } from '@reduxjs/toolkit';
import { getApi, postApi, patchApi } from '../api/api.js';


    
export const fetchData = createAsyncThunk('data/fetchData', async (path) => {
    return await getApi(path);
    });

export const postData = createAsyncThunk('data/postData', async ({ path, data }) => {
        return await postApi(path, data);
    });

export const fetchUserById = createAsyncThunk('data/fetchUserById', async (memberId) => {
    const response = await getApi(`members/${memberId}`); 
    return { memberId, user: response };
    });

export const patchData = createAsyncThunk('data/patchData', async ({ path, data }) => {
        return await patchApi(path, data);
    });

export const fetchAnswersByQuestionId = createAsyncThunk('data/fetchAnswersByQuestionId', async (questionId) => {
        const response = await getApi(`questions/${questionId}/answers`);
        
        return response;
    });
    
export const dataSlice = createSlice({
        name: 'data',
        initialState: {
        items: [],
        question: null,
        users: {},
        answers: [],
        status: 'idle',
        },
        extraReducers: (builder) => {
        builder
            // .addCase(fetchUserById.fulfilled, (state, action) => {
            //     state.users.push(action.payload.user); // users 배열을 사용할 때 사용? name 부분이 제각각 들어감
            // })
            .addCase(fetchUserById.fulfilled, (state, action) => {
                const { memberId, user } = action.payload;
                state.users[memberId] = user; // 사용자 ID를 키로 사용하여 사용자 정보 저장
            })
            .addCase(fetchData.pending, (state) => {
            state.status = 'loading';
            })
            .addCase(fetchData.fulfilled, (state, action) => {
                state.status = 'succeeded';
                if (Array.isArray(action.payload)) {
                    state.items = action.payload; // 배열 응답을 items에 저장
                } else {
                    state.question = action.payload; // 객체 응답을 question에 저장
                }
            })
            .addCase(fetchAnswersByQuestionId.fulfilled, (state, action) => {
                state.answers = action.payload;
            })
            .addCase(patchData.fulfilled, (state, action) => {
                const updatedPoint = action.payload.point;
                state.question.point = updatedPoint;
            })
            .addCase(fetchData.rejected, (state) => {
                state.status = 'failed';
            });
        },
    });

    
    export default dataSlice.reducer;



