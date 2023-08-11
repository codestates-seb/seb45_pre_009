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
            <div id='mainbar' className=''>
                <div className='flex'>
                    <h1 className='flex-1 text-3xl mb-4 '>Top Questions</h1>
                    <div className='ml-3'><a className=' p-3 rounded-lg border bg-blue-500 text-white ' href="/main">Ask Question</a></div>
                </div>
                <div>
                    <div className=''>
                        <div className='w-full flex'>
                            <div className='flex flex-1 flex-reverse-row justify-end mb-4 '>
                                <a href='/main' className='p-2.5 border border-black rounded-l-md'>Interesting</a>
                                <a href='/main' className='p-2.5 border border-black' >Bountied</a>
                                <a href='/main' className='p-2.5 border border-black' >Hot</a>
                                <a href='/main' className='p-2.5 border border-black' >Week</a>
                                <a href='/main' className='p-2.5 border border-black rounded-r-md'>Month</a>
                            </div>
                        </div>
                    </div>
                </div>
                <div id='qlist-wrapper' className='flex flex-1 flex-col border-t'>
                    <div>
                        {data && data.map((item) => (
                            <div className='p-4 relative border-b flex w-full ' key={item.id}>
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
                                            <a>{item.name}</a>
                                            <div>{item.created_at}</div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        ))}
                    </div>
                </div>
            </div>
        </div>
    )



}

