import { Link } from 'react-router-dom';
import React, { useState } from 'react';
import { useDispatch } from 'react-redux';
import { postData } from '../slicer/main';
import MyEditor from '../components/ckeditor5/editor';



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

    // function CreateQuestion() {
    //     const history = useHistory();
      
    //     const handleSubmit = async (question) => {
    //       const id = await createQuestionInDB(question);
    //       history.push(`/question/${id}`);
    //     };
      
    //     return (
    //       // 폼 렌더링
    //     );
    //   }

    return (
        <>
            <div id='container' className='max-w-full w-full justify-center  '>
                <div id='content' className='min-h-[750px] overflow-visible max-w-[1264px] p-[24px] pt-0'>
                    <div id='boxborder' className='w-full'>
                        <div id='upperbox' className='flex flex-col '>
                            <div className='h-[130px] w-full bg-no-repeat bg-right-bottom flex items-center'>
                                <h1 className='font-semibold text-[27px] my-[1em] mt-[24px] '>Ask a public question</h1>
                            </div>
                            <div className='w-full mt-[16px] block'>
                                <div className='w-full flex mb-[16px] items-center  '>
                                    <div className='w-full p-[24px] bg-[#ebf4fb] border rounded-md border-[rgb(195,229,255)]'>
                                        <h2 className='font-normal text-[21px] mb-8  '>Writing a good question</h2>
                                        <p className='text-[15px] mb-0 clear-both mt-0 '>You’re ready to <Link to='/' className='text-blue-600 ' >ask</Link> a <Link to='/' className='text-blue-600 ' >programming-related question</Link> and this form will help guide you through the process.</p>
                                        <p className='mb-[1em] text-[15px] clear-both '>Looking to ask a non-programming question? See <Link to='/' className='text-blue-600 '>the topics here</Link> to find a relevant site.</p>
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
                        <form id='askquestion' className='  '>
                            <main className=' mb-[48px]  '>
                                <div className=' flex w-full bg-white rounded-[4px] border  '>
                                    <div className=' p-[24px] gap-[16px] w-full '>
                                        <div className=' flex flex-col mx-0 my-[-2px]  '>
                                            <div className=' flex flex-col mx-0 my-[2px]  ' >
                                                <div className='  '>
                                                    <label className=' cursor-pointer text-[15px] font-semibold '>
                                                        Title
                                                    </label>
                                                </div>
                                                <div className=' flex  '>
                                                    <div className=' my-[2px]  '>
                                                        <label className=' text-[12px]  '>
                                                            Be specific and imagine you’re asking a question to another person.
                                                        </label>
                                                    </div>
                                                </div>
                                            </div>
                                            <div className=' mx-0 my-[2px] relative flex  '>
                                                <input className='border w-full rounded-[6px] border-gray-300 px-[9px] py-[7px] ' placeholder='Write here...' value={title} onChange={e => setTitle(e.target.value)} > 
                                                </input>
                                            </div>
                                            <button className='mt-[8px] p-[14px] '>
                                            </button>
                                        </div>
                                    </div>
                                </div>
                                <div id='content' className=' w-full flex mt-[12px] '>
                                    <div className=' w-full bg-white rounded-[4px] border min-h-[320px]'>
                                        <div className=' p-[24px]  ' >
                                            <div className=' flex flex-col my-[-2px]  '>
                                                <div className=' my-[2px]  '>
                                                    <label className=' text-[15px] ' >
                                                        What are the details of your problem?
                                                        <p className=' text-[12px] ' >
                                                            Introduce the problem and expand on what you put in the title. Minimum 20 characters.
                                                        </p>
                                                    </label>
                                                </div>
                                                <div className='  '>
                                                    <MyEditor onContentChange={setContent}></MyEditor>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div>
                                    <button className='mt-[10px] p-[0.8em] rounded-[6px] border border-transparent text-[13px] leading-normal bg-[hsl(206,100%,52%);] text-white whitespace-nowrap text-center relative' onClick={handleSubmit}>
                                        Send
                                    </button>
                                </div>
                            </main>
                        </form>
                    </div>
                </div>
            </div>
        </>
    )



}

