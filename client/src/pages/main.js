import React, { useState, useEffect } from 'react';
import { useSelector, useDispatch } from 'react-redux';
import { fetchData, fetchUserById } from '../slicer/main';

export default function Main() {

    const dispatch = useDispatch();
    const data = useSelector((state) => state.data.items);
    const status = useSelector((state) => state.data.status);
    const users = useSelector((state) => state.data.users);

    useEffect(() => {
        if (status === 'idle') {
            dispatch(fetchData('question'));
        }
    }, [status, dispatch]);

    useEffect(() => {
        data.forEach((item) => {
        dispatch(fetchUserById(item.user_id));
        });
    }, [data, dispatch]);

    return (
        <div id='container' className='flex justify-between '>
            <div id='content' className='p-6 h-full max-w-6xl w-full '>
                <div id='mainbar' className='float-left w-full max-w-xl relative '>
                    <div className='flex'>
                        <h1 className='flex-1 text-3xl mb-4 '>Top Questions</h1>
                        <div className='ml-3 '><a className=' p-3 rounded-lg border text-xs leading-normal bg-blue-500 text-white whitespace-nowrap text-center relative' href="/main">Ask Question</a></div>
                    </div>
                    <div>
                        <div className=''>
                            <div className='w-full flex'>
                                <div className='flex flex-1 flex-reverse-row justify-end mb-4 '>
                                    <a href='/main' className='p-2 border border-black rounded-l-md'>Interesting</a>
                                    <a href='/main' className='p-2 border border-black' >Bountied</a>
                                    <a href='/main' className='p-2 border border-black' >Hot</a>
                                    <a href='/main' className='p-2 border border-black' >Week</a>
                                    <a href='/main' className='p-2 border border-black rounded-r-md'>Month</a>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div id='qlist-wrapper' className='flex flex-1 flex-col border-t'>
                        <div>
                            {data && data.map((item) => (
                                <div className='p-4 relative border-b flex w-full justify-center ' key={item.id}>
                                    <div className='flex flex-col justify-between mr-4 mb-4 gap-1'>
                                        <div className='text-xs'>0 votes</div>
                                        <div className='text-xs'>0 answers</div>
                                        <div className='text-xs'>0 views</div>
                                    </div>
                                    <div className=''>
                                        <div className='text-xl mb-1 pr-6 text-blue-600'><a href='/main'>{item.title}</a></div>
                                        <div className='flex flex-wrap flex-1 items-center justify-between gap-y-2 gap-x-2 relative' >
                                            <div className='text-left'>tag,tag,tag</div>
                                            <div className='ml-auto text-right flex-wrap flex justify-end'>
                                                <a href='/main'>{users[item.user_id]?.name || 'Loading...'}</a>
                                                <div>{item.created_at}</div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            ))}
                        </div>
                    </div>
                </div>
                <div id='right-sidebar' className='float-right w-72 ml-6 '>
                        <div>오른쪽에 두는 것들</div>
                </div>
            </div>
        </div>
    )



}

