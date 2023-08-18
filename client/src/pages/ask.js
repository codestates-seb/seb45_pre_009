
import React, { useState } from 'react';
import { useDispatch } from 'react-redux';
import { postData } from '../slicer/main';



export default function Ask() {

    const dispatch = useDispatch();

    const [title, setTitle] = useState('');
    const [content, setContent] = useState('');

    const handleSubmit = () => {
        const myData = { 
            title,
            content
        };
        console.log(myData)
        dispatch(postData({ path: 'questions', data: myData }));
    };

    // const handleSubmit = () => {
    //     const myData = { 
    //         user_id: 0, 
    //         title, 
    //         content, 
    //         point: 0, 
    //         views: 0, 
    //         created_at: new Date(), 
    //         modified_at: "" };
    //     dispatch(postData({ path: 'question', data: myData }));
    // };

    // bg-[url(https://cdn.sstatic.net/Img/ask/background.svg?v=2e9a8205b368)]

    return (
        <>
            <div id='container' className='max-w-full w-full justify-center '>
                <div id='content' className='min-h-[750px] overflow-visible max-w-[1264px] p-[24px] pt-0'>
                    <div id='boxborder' className='w-full'>
                        <div id='upperbox' className='flex flex-col '>
                            <div className='h-[130px] w-full bg-no-repeat bg-right-bottom flex items-center'>
                                <h1 className='font-semibold text-[27px] my-[1em] mt-[24px] '>Ask a public question</h1>
                            </div>
                            <div className='w-full mt-[16px] block'>
                                <div className='w-full flex mb-[16px] items-center  '>
                                    <div className='w-[70%] p-[24px] bg-[#ebf4fb] border rounded-md border-[rgb(195,229,255)]'>
                                        <h2 className='font-normal text-[21px] mb-8  '>Writing a good question</h2>
                                        <p className='text-[15px] mb-0 clear-both mt-0 '>You’re ready to <a className='text-blue-600 ' href='/'>ask</a> a <a className='text-blue-600 ' href='/'>programming-related question</a> and this form will help guide you through the process.</p>
                                        <p className='mb-[1em] text-[15px] clear-both '>Looking to ask a non-programming question? See <a className='text-blue-600 ' href='/'>the topics here</a> to find a relevant site.</p>
                                        <h5 className='text-[13px] '>steps</h5>
                                        <ul className='mb-0 text-[13px] list-disc ml-[30px]'>
                                            <li>Summarize your problem in a one-line title.</li>
                                            <li>Describe your problem in more detail.</li>
                                            <li>Describe what you tried and what you expected to happen.</li>
                                            <li>Add “tags” which help surface your question to members of the community.</li>
                                            <li>Review your question and post it to the site.</li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <form>
                            <div>
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
                        </form>
                    </div>
                </div>
            </div>
        </>
    )



}

