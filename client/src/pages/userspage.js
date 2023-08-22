
import React, { useEffect } from 'react';
import { useSelector, useDispatch } from 'react-redux';
import { fetchData } from '../slicer/main';
import Sidebar from '../components/Sidebar/Sidebar';
// import { api } from '../api/api';
import { Link } from 'react-router-dom';

export default function UsersPage() {

    const dispatch = useDispatch();
    const data = useSelector((state) => state.data.items);
    const status = useSelector((state) => state.data.status);

    useEffect(() => {
        if (status === 'idle') {
            dispatch(fetchData('members'));
        }
    }, [status, dispatch]);
    

    return (
        <>
            <div id='container' className='flex justify-between z-0 w-full '>
                <div id='content' className='block p-6 h-full w-full '>
                    <div id='mainbar' className='float-left block relative w-full '>
                        <div className='flex mb-[12px] flex-wrap'>
                            <h1 className='flex-auto leading-[1.3] text-[27px] mb-[12px] mr-[12px]'>Users</h1>
                        </div>
                        <div>
                            <div>
                                <div className=''>
                                <div className='w-full flex'>
                                    <div className='flex flex-1 flex-reverse-row justify-between items-center mb-4 text-[13px] '>
                                        <div id='' className='flex flex-auto mr-auto text-[17px]'>
                                            {data.length} users
                                        </div>
                                        <div className='flex'>
                                            <Link to='/' className='p-2 -mr-[1px] -mb-[1px] border border-gray-400 rounded-l-md'>Interesting</Link>
                                            <Link to='/' className='p-2 -mr-[1px] -mb-[1px] border border-gray-400' >Bountied</Link>
                                            <Link to='/' className='p-2 -mr-[1px] -mb-[1px] border border-gray-400' >Hot</Link>
                                            <Link to='/' className='p-2 -mr-[1px] -mb-[1px] border border-gray-400' >Week</Link>
                                            <Link to='/' className='p-2 -mr-[1px] -mb-[1px] border border-gray-400 rounded-r-md'>Month</Link>
                                        </div>
                                    </div>
                                </div>
                                </div>
                            </div>
                        </div>
                        <div id='userbrowser' className='border-t'>
                            <div className='grid grid-cols-[repeat(1,minmax(0,1fr))] nm640980:grid-cols-[repeat(2,minmax(0,1fr))] nm9801264:grid-cols-[repeat(3,minmax(0,1fr))] min1264:grid-cols-[repeat(4,minmax(0,1fr))] '>
                                {data && data.map((item) => (
                                    <div className='p-4 relative border-b flex vx:flex-col ' key={item.memberId}>
                                        <div>{item.displayName}</div>
                                    </div>
                                ))}
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </>
    )



}

