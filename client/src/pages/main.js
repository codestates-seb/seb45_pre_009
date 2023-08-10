import React, { useEffect } from 'react';
import { useSelector, useDispatch } from 'react-redux';
import { fetchData } from '../slicer/main';

export default function Main() {

    const dispatch = useDispatch();
    const data = useSelector((state) => state.data.items);
    const status = useSelector((state) => state.data.status);

    useEffect(() => {
        if (status === 'idle') {
          dispatch(fetchData('question'));
        }
    }, [status, dispatch]);

    return (
        <div className='p-6 h-screen w-full'>
            <div id='main' className=''>
                <h1 className='text-3xl'>Top Questions</h1>
                <div className='pr-4'><a href="/main">Ask Question</a></div>
                <div className=''>
                    <div className='w-full'>
                        <div className='flex flex-1 flex-reverse-row justify-end'>
                            <a href='/main' className='p-2.5 border border-black rounded-l-md'>Interesting</a>
                            <a href='/main' className='p-2.5 border border-black' >Bountied</a>
                            <a href='/main' className='p-2.5 border border-black' >Hot</a>
                            <a href='/main' className='p-2.5 border border-black' >Week</a>
                            <a href='/main' className='p-2.5 border border-black rounded-r-md'>Month</a>
                        </div>
                    </div>
                </div>
                <div id='qlist-wrapper' className='flex flex-1 flex-col border-t'>
                    <div>
                        {data && data.map((item) => (
                            <div className='p-4 relative border-b flex' key={item.id}>
                                <div className='flex-col'>
                                    <div>vote</div>
                                    <div>answer</div>
                                    <div>view</div>
                                </div>
                                <div>
                                    <div>{item.title}</div>
                                    <div>{item.content}</div>
                                </div>
                            </div>
                        ))}
                    </div>
                </div>
            </div>
        </div>
    )



}

