import { useParams } from 'react-router-dom';
import React, { useEffect } from 'react';
import { useSelector, useDispatch } from 'react-redux';
import { fetchData, fetchUserById } from '../slicer/main';
import Sidebar from '../components/Sidebar/Sidebar';
import moment from 'moment-timezone';

function QuestionPage() {
    const { id } = useParams();
    const dispatch = useDispatch();
    const question = useSelector((state) => state.data.question);
    const status = useSelector((state) => state.data.status);
    const users = useSelector((state) => state.data.users);

    function momenti (time) {
        return moment
            .utc([time[0],time[1] - 1,time[2],time[3],time[4],time[5],])
            .tz("Asia/Seoul")
            .fromNow()
    }

    useEffect(() => {
    if (status === 'idle') {
        dispatch(fetchData(`questions/${id}`));
    }
    }, [status, dispatch, id]);

    useEffect(() => {
        if (question && question.memberId) {
            dispatch(fetchUserById(question.memberId));
        }
    }, [question, dispatch]);

    return (
        <div className='flex justify-between z-0 w-full '>
            <Sidebar></Sidebar>
                <div id='content' className='block p-6 h-full max-w-6xl w-full'>
                    <div>
                    {question ? (
                        <div id='inner content'>
                            <div id='question header' className=' flex flex-row flex-nowrap justify-between '>
                                <h1 className=' mb-[8px] break-words text-[27px] '>
                                    {question.title}
                                </h1>
                                <div className='flex ml-3 mb-[12px]'>
                                    <a href="/questions/ask" className='p-[0.8em] rounded-[6px] border border-transparent text-[13px] leading-normal bg-[hsl(206,100%,52%);] text-white whitespace-nowrap text-center relative '>
                                        Ask Question
                                    </a>
                                </div>
                            </div>
                            <div id='createdat etc' className='flex pb-[8px] mb-[16px] flex-wrap border-b border-b-[#e3e6e8] text-[13px]'>
                                <div className='mb-[8px] mr-[16px] flex-nowrap  '>
                                    <span className='mr-[2px] text-[hsl(210,8%,45%)]'>Asked</span>
                                    <span>
                                        {question.createdAt 
                                            ? momenti(question.createdAt)
                                            : "Loading..."}
                                    </span>
                                </div>
                                <div className='mb-[8px] mr-[16px] flex-nowrap  '>
                                    <span className='mr-[2px] text-[hsl(210,8%,45%)]'>Modified</span>
                                    <span>
                                        {question.modifiedAt 
                                            ? momenti(question.modifiedAt)
                                            : momenti(question.createdAt)}
                                    </span>
                                </div>
                                <div className='mb-[8px] flex-nowrap  '>
                                    <span className='mr-[2px] text-[hsl(210,8%,45%)]'>Viewed</span>
                                    <span>{question.views !== undefined ? question.views : '0'}</span>
                                </div>
                            </div>
                            <div id='mainbar' className='float-left block relative vv:w-[calc(100%-300px-24px)] w-full '>
                                <div id='question' className='clear-both '>
                                    <div id='post-layout' className=' grid grid-cols-[max-content,1fr]  '>
                                        <div id='vote' className='w-auto pr-[16px] align-top col-[1] min-h-[300px]  '>
                                            <div className='flex flex-col items-stretch justify-center text-[hsl(210,8%,75%)] '>
                                                <button className=' m-[2px] cursor-pointer items-center rounded-[1000px] border p-[10px] hover:bg-orange-100 '>
                                                    <div className=' h-[18px] w-[18px] items-center '>
                                                        <div className='text-[14px] text-black'>상</div>
                                                    </div>
                                                </button>
                                                <div className='m-[2px] flex font-semibold text-[19px] text-black py-[4px] items-center flex-col '>
                                                    {question.point !== undefined ? question.point : '0'}
                                                </div>
                                                <button className=' m-[2px] cursor-pointer items-center rounded-[1000px] border p-[10px] hover:bg-orange-100 '>
                                                    <div className=' h-[18px] w-[18px] items-center '>
                                                        <div className='text-[14px] text-black'>하</div>
                                                    </div>
                                                </button>
                                                <button className=' cursor-pointer py-[4px] text-center items-center justify-center content-center '>
                                                    <div className=' mx-auto h-[18px] w-[18px] items-center text-[13px] text-gray-400 hover:text-blue-600 '>
                                                        <div className=' '>즐</div>
                                                    </div>
                                                </button>
                                                <button className=' cursor-pointer py-[4px] text-center items-center justify-center content-center '>
                                                    <div className=' mx-auto h-[18px] w-[18px] items-center text-[13px] text-gray-400 hover:text-blue-600 '>
                                                        <div className=' '>히</div>
                                                    </div>
                                                </button>
                                            </div>
                                        </div>
                                        <div id='post' className=' align-top pr-[16px] grid-cols-[2] w-auto min-w-0  '>
                                            <div className=' w-full min-h-[200px] '>
                                                {question.content}
                                            </div>
                                            <div className=' mb-[12px] mt-[24px] '>
                                                <div className=' clear-both mb-[10px] flex flex-col mx-0 m-[-2]  '>
                                                    <div className=' mx-0 m-[2px] relative flex flex-wrap  '>
                                                        <ul className=' list-none ml-0 mb-[1em] text-[12px]  '>
                                                            <li className=''>
                                                                <a href='/' className='block text-[hsl(205,47%,42%)] bg-[hsl(205,46%,92%)] border border-transparent rounded-md px-[6px] py-[4px] mr-[1px] mb-[1px] '>tag</a>
                                                            </li>
                                                        </ul>
                                                    </div>
                                                </div>
                                            </div>
                                            <div className=' mb-0 block '>
                                                <div className=' flex pt-[4px] my-[16px] items-start justify-end flex-wrap mx-0  '>
                                                    <div className=' mx-0 m-[4px] w-[96px] mr-[16px] flex-auto  ' >
                                                        <div className=' pt-[2px]  '>
                                                            <div className=' flex flex-wrap m-[-4px] text-[13px] ' >
                                                                <div className=' m-[4px] block text-gray-500 hover:text-gray-300 ' >
                                                                    <a href='/'>Share</a>
                                                                </div>
                                                                <div className=' m-[4px] block text-gray-500 hover:text-gray-300 ' >
                                                                    <a href='/'>Edit</a>
                                                                </div>
                                                                <div className=' m-[4px] block text-gray-500 hover:text-gray-300 ' >
                                                                    <a href='/'>Follow</a>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div className=' w-[200px] align-top m-[4px] rounded-[4px] bg-sky-100 '>
                                                        <div className=' px-[7px] py-[6px] table ' >
                                                            <div className=' mt-[1px] mb-[4px] text-[12px] text-gray-500 '>
                                                                <a href='/'>edited 
                                                                    {question.modifiedAt 
                                                                        ? momenti(question.modifiedAt)
                                                                        : momenti(question.createdAt)}
                                                                </a>
                                                            </div>
                                                            <div className=' float-left w-[32px] h-[32px] rounded-[1px] border border-black '>
                                                                
                                                            </div>
                                                            <div className=' float-left ml-[8px] w-[calc(100%-40px)] break-all text-[13px] '>
                                                                <a className='text-blue-500' href='/'>{users[question.memberId]?.displayName || 'name...'}</a>
                                                                <div className=' text-[13px] '>
                                                                    {users[question.memberId]?.reputation || '0'}
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <span></span>
                                        <div id='comment' className='pr-[16px] grid-cols-[2] w-auto '>
                                            <div className=' text-[13px] text-gray-400 '>
                                                <a href='/' className='p-[0,3px,2px] cursor-pointer  '>
                                                    Add a comment
                                                </a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div id='right-sidebar' className='relative float-right block vx:w-full vv:w-72 vv:ml-6 vx:float-none vx:clear-both vx:m-[0,auto] '>
                                <div className='mb-4 relative block border rounded border-[hsl(47,65%,84%);] vx:w-full bg-[hsl(47,87%,94%)] '>
                                    <ul className='block p-0 m-0  '>
                                        <li className='text-[#525960] px-[15px] py-[12px] text-[12px] border-b border-[hsl(47,65%,84%);] bg-[#fbf3d5] font-bold'>
                                            The Overflow Blog
                                        </li>
                                        <li className='px-[16px] my-[12px] h-full flex '>
                                            <div className='flex-shrink-0 basis-[8.33333%] text-[14px]'>
                                                펜
                                            </div>
                                            <div className='min-w-0 text-[13px] text-[#232629]'>
                                                <a href='/' className=''>Want better answers from your data? Ask better questions</a>
                                            </div>
                                        </li>
                                        <li className='px-[16px] my-[12px] h-full flex'>
                                            <div className='flex-shrink-0 basis-[8.33333%] text-[14px]'>
                                                펜
                                            </div>
                                            <div className='min-w-0 text-[13px] text-[#232629]'>
                                                <a href='/' className=''>Making event-driven development predictable with Discover</a>
                                                <div className=' text-[#6a737c] '>sponsored post</div>
                                            </div>
                                        </li>
                                        <li className='text-[#525960] px-[15px] py-[12px] text-[12px] border-b border-t border-[hsl(47,65%,84%);] bg-[#fbf3d5] font-bold'>
                                            Featured on Meta
                                        </li>
                                        <li className='px-[16px] my-[12px] h-full flex'>
                                            <div className='flex-shrink-0 basis-[8.33333%] text-[14px]'>
                                                말
                                            </div>
                                            <div className='min-w-0 text-[13px] text-[#3b4045]'>
                                                <a href='/' className=''>Moderation strike: Results of negotiations</a>
                                            </div>
                                        </li>
                                        <li className='px-[16px] my-[12px] h-full flex'>
                                            <div className='flex-shrink-0 basis-[8.33333%] text-[14px]'>
                                                말
                                            </div>
                                            <div className='min-w-0 text-[13px] text-[#3b4045]'>
                                                <a href='/' className=''>Our Design Vision for Stack Overflow and the Stack Exchange network</a>
                                            </div>
                                        </li>
                                        <li className='px-[16px] my-[12px] h-full flex'>
                                            <div className='flex-shrink-0 basis-[8.33333%] text-[14px]'>
                                                책
                                            </div>
                                            <div className='min-w-0 text-[13px] text-[#3b4045]'>
                                                <a href='/' className=''>Temporary policy: Generative AI (e.g., ChatGPT) is banned</a>
                                            </div>
                                        </li>
                                        <li className='px-[16px] my-[12px] h-full flex'>
                                            <div className='flex-shrink-0 basis-[8.33333%] text-[14px]'>
                                                책
                                            </div>
                                            <div className='min-w-0 text-[13px] text-[#3b4045]'>
                                                <a href='/' className=''>Collections: A New Feature for Collectives on Stack Overflow</a>
                                            </div>
                                        </li>
                                        <li className='px-[16px] my-[12px] h-full flex'>
                                            <div className='flex-shrink-0 basis-[8.33333%] text-[14px]'>
                                                책
                                            </div>
                                            <div className='min-w-0 text-[13px] text-[#3b4045]'>
                                                <a href='/' className=''>Preview of Search and Question-Asking Powered by GenAI</a>
                                            </div>
                                        </li>
                                        <li className='px-[16px] my-[12px] h-full flex'>
                                            <div className='flex-shrink-0 basis-[8.33333%] text-[14px]'>
                                                책
                                            </div>
                                            <div className='min-w-0 text-[13px] text-[#3b4045]'>
                                                <a href='/' className=''>Call for volunteer reviewers for an updated search experience: OverflowAI Search</a>
                                            </div>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    ) : (
                                    <p>Loading or no question available</p>
                    )}
                </div>
            </div>
        </div>
    );
}

export default QuestionPage;