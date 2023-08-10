import React, { useEffect } from 'react';
import { useSelector, useDispatch } from 'react-redux';
import { fetchData } from '../slicer/main';

export default function Main() {

    const dispatch = useDispatch();
    const data = useSelector((state) => state.data.items);
    const status = useSelector((state) => state.data.status);

    useEffect(() => {
        if (status === 'idle') {
          dispatch(fetchData('user'));
        }
    }, [status, dispatch]);

    return (
        <div>
            <h1 className='text-3xl'>Top Questions</h1>
            <div className=''><a href="/main">Ask Question</a></div>
            <div>
                {status === 'loading' ? (
                    'Loading...'
                ) : (   
                <ul>
                    {data && data.map((item) => (
                        <li key={item.id}>{item.name}</li>
                    ))}
                </ul>
                )}
            </div>
        </div>
    )



}

