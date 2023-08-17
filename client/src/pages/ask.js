
import React, { useState, useEffect } from 'react';
import { useSelector, useDispatch } from 'react-redux';
import { postData } from '../slicer/main';
import Header from "../components/Header/Header"


export default function Ask() {

    const dispatch = useDispatch();

    const [title, setTitle] = useState('');
    const [content, setContent] = useState('');

    const handleSubmit = () => {
        const myData = { 
            user_id: 0, 
            title, 
            content, 
            point: 0, 
            views: 0, 
            created_at: new Date(), 
            modified_at: "" };
        dispatch(postData({ path: 'question', data: myData }));
    };



    return (
        <>
            <Header/>
            <div className='flex flex-col justify-center items-center'>
                <div>Ask a public question</div>
                <div>
                    title
                    <input className='border' value={title} onChange={e => setTitle(e.target.value)} />
                </div>
                <div>
                    content
                    <input className='border' value={content} onChange={e => setContent(e.target.value)} />
                </div>
                <div>
                    <button className='border' onClick={handleSubmit}>
                        send
                    </button>
                </div>
            </div>
            
        </>
    )



}

