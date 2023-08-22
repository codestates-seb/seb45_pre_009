
import React, { useEffect } from 'react';
import { useSelector, useDispatch } from 'react-redux';
import { fetchData, fetchUserById } from '../slicer/main';
import moment from 'moment-timezone';
import { Link, useParams } from 'react-router-dom';


export default function Main() {
    const { id } = useParams();
    const dispatch = useDispatch();
    const data = useSelector((state) => state.data.items);
    const status = useSelector((state) => state.data.status);
    const users = useSelector((state) => state.data.users);

    useEffect(() => {
        if (status === 'idle') {
            dispatch(fetchData('questions?page=0&size=10'));
        }
    }, [status, dispatch, id]);

    useEffect(() => {
        data.forEach((item) => {
        dispatch(fetchUserById(item.memberId));
        });
    }, [data, dispatch]);
    

    return (
        <>
            <div id='container' className='flex justify-between z-0 w-full '>
                <div id='content' className='block p-6 max-w-6xl w-full '>
                    <div id='mainbar' className='float-left block relative vv:w-[calc(100%-300px-24px)] w-full '>
                    <div className='flex mb-[12px] flex-wrap'>
                            <h1 className='flex-auto leading-[1.3] text-[27px] mb-[12px] mr-[12px]'>Top Questions</h1>
                            <div className='flex ml-3 mb-[12px]'>
                                <Link to="/questions/ask" className='p-[0.8em] rounded-[6px] border border-transparent text-[13px] leading-normal bg-[hsl(206,100%,52%);] text-white whitespace-nowrap text-center relative '>
                                    Ask Question
                                </Link>
                            </div>
                        </div>
                        <div>
                            <div className=''>
                                <div className='w-full flex'>
                                    <div className='flex flex-1 flex-reverse-row justify-between items-center mb-4 text-[13px] '>
                                        <div className='flex flex-auto mr-auto text-[17px]'>
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
                        <div id='qlist-wrapper' className=''>
                            <div id='question-mini-list' className='mb-8'>
                                <div className='w-[auto] float-none mb-[20px] clear-both ml-[-24px] border-t '>
                                    {data && data.map((item) => (
                                        <div className='p-4 relative border-b flex vx:flex-col ' key={item.memberId}>
                                            <div className='flex vv:flex-col vx:w-auto vv:w-[108px] vx:flex-row vv:flex-wrap vv:content-end vv:flex-shrink-0 mr-4 vv:mb-4 vx:mb-1 gap-[6px]'>
                                                <div className='inline-flex gap-[0.3em] justify-end content-end whitespace-nowrap border border-transparent '>
                                                    <span className='text-xs'>0</span><span className='text-xs'>votes</span>
                                                </div>
                                                <div className='inline-flex gap-[0.3em] justify-end content-end whitespace-nowrap border border-transparent text-[#6a737c] '>
                                                    <span className='text-xs'>0</span><span className='text-xs'>answers</span>
                                                </div>
                                                <div className='inline-flex gap-[0.3em] justify-end content-end whitespace-nowrap border border-transparent text-[#6a737c] '>
                                                    <span className='text-xs'>{item.views !== undefined ? item.views : '0'}</span><span className='text-xs'>views</span>
                                                </div>
                                            </div>
                                            <div className='max-w-full flex-grow'>
                                                <h3 className='mb-1 pr-6 text-[#0074cc]'><Link to={`/questions/${item.questionId}`}>{item.title}</Link></h3>
                                                <div className='flex flex-wrap flex-1 items-center justify-between gap-y-2 gap-x-2 relative text-[12px]' >
                                                    <ul className='ml-0 '>
                                                        <li className='flex flex-row'>
                                                            <Link to='/' className='block text-[12px] text-[hsl(205,47%,42%)] bg-[hsl(205,46%,92%)] border border-transparent rounded-md px-[6px] py-[4px] mr-[1px] mb-[1px] '>
                                                            tag1
                                                            </Link>
                                                            <Link to='/' className='block text-[12px] text-[hsl(205,47%,42%)] bg-[hsl(205,46%,92%)] border border-transparent rounded-md px-[6px] py-[4px] mr-[1px] mb-[1px] '>
                                                            tag2
                                                            </Link>
                                                            <Link to='/' className='block text-[12px] text-[hsl(205,47%,42%)] bg-[hsl(205,46%,92%)] border border-transparent rounded-md px-[6px] py-[4px] mr-[1px] mb-[1px] '>
                                                            looooooooooooooooooooooon-tag
                                                            </Link>
                                                            <Link to='/' className='block text-[12px] text-[hsl(205,47%,42%)] bg-[hsl(205,46%,92%)] border border-transparent rounded-md px-[6px] py-[4px] mr-[1px] mb-[1px] '>
                                                            tag3
                                                            </Link>
                                                            <Link to='/' className='block text-[12px] text-[hsl(205,47%,42%)] bg-[hsl(205,46%,92%)] border border-transparent rounded-md px-[6px] py-[4px] mr-[1px] mb-[1px] '>
                                                            tag4
                                                            </Link>
                                                        </li>
                                                    </ul>
                                                    <div className='ml-auto text-right flex-wrap flex justify-end gap-1'>
                                                        <Link to='/' className='text-[#0074cc]'>{users[item.memberId]?.displayName || 'name...'}</Link>
                                                        <div className='text-[#525960] font-bold'>
                                                            {users[item.memberId]?.reputation !== undefined ? users[item.memberId]?.reputation : '0'}
                                                        </div>
                                                        <div className='text-[#6a737c]'>
                                                            createdAt &nbsp;
                                                            {item.createdAt
                                                                ? moment
                                                                    .utc([
                                                                    item.createdAt[0],
                                                                    item.createdAt[1] - 1,
                                                                    item.createdAt[2],
                                                                    item.createdAt[3],
                                                                    item.createdAt[4],
                                                                    item.createdAt[5],
                                                                    ])
                                                                    .tz("Asia/Seoul")
                                                                    .fromNow()
                                                                : "Loading..."}
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    ))}
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
                                            <Link to='/' className=''>Want better answers from your data? Ask better questions</Link>
                                        </div>
                                    </li>
                                    <li className='px-[16px] my-[12px] h-full flex'>
                                        <div className='flex-shrink-0 basis-[8.33333%] text-[14px]'>
                                            펜
                                        </div>
                                        <div className='min-w-0 text-[13px] text-[#232629]'>
                                            <Link to='/' className=''>Making event-driven development predictable with Discover</Link>
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
                                            <Link to='/' className=''>Moderation strike: Results of negotiations</Link>
                                        </div>
                                    </li>
                                    <li className='px-[16px] my-[12px] h-full flex'>
                                        <div className='flex-shrink-0 basis-[8.33333%] text-[14px]'>
                                            말
                                        </div>
                                        <div className='min-w-0 text-[13px] text-[#3b4045]'>
                                            <Link to='/' className=''>Our Design Vision for Stack Overflow and the Stack Exchange network</Link>
                                        </div>
                                    </li>
                                    <li className='px-[16px] my-[12px] h-full flex'>
                                        <div className='flex-shrink-0 basis-[8.33333%] text-[14px]'>
                                            책
                                        </div>
                                        <div className='min-w-0 text-[13px] text-[#3b4045]'>
                                            <Link to='/' className=''>Temporary policy: Generative AI (e.g., ChatGPT) is banned</Link>
                                        </div>
                                    </li>
                                    <li className='px-[16px] my-[12px] h-full flex'>
                                        <div className='flex-shrink-0 basis-[8.33333%] text-[14px]'>
                                            책
                                        </div>
                                        <div className='min-w-0 text-[13px] text-[#3b4045]'>
                                            <Link to='/' className=''>Collections: A New Feature for Collectives on Stack Overflow</Link>
                                        </div>
                                    </li>
                                    <li className='px-[16px] my-[12px] h-full flex'>
                                        <div className='flex-shrink-0 basis-[8.33333%] text-[14px]'>
                                            책
                                        </div>
                                        <div className='min-w-0 text-[13px] text-[#3b4045]'>
                                            <Link to='/' className=''>Preview of Search and Question-Asking Powered by GenAI</Link>
                                        </div>
                                    </li>
                                    <li className='px-[16px] my-[12px] h-full flex'>
                                        <div className='flex-shrink-0 basis-[8.33333%] text-[14px]'>
                                            책
                                        </div>
                                        <div className='min-w-0 text-[13px] text-[#3b4045]'>
                                            <Link to='/' className=''>Call for volunteer reviewers for an updated search experience: OverflowAI Search</Link>
                                        </div>
                                    </li>
                                </ul>
                            </div>
                            <div className='mb-4 relative bg-white border rounded border-[hsl(210,8%,85%);] vx:w-full '>
                                <h2 className='py-[12px] px-[15px] bg-[hsl(210,8%,97.5%);] text-[hsl(210,8%,35%)] text-[15px]   '>
                                    Custom Filters
                                </h2>
                                <ul className='py-[4px] px-[15px] border-t border-[hsl(210,8%,85%);]  '>
                                    <li className='text-[hsl(210,8%,65%)] my-3 text-[13px]  '>
                                        Create a custom filter
                                    </li>
                                </ul>
                            </div>
                            <div className='mb-4 relative bg-white border rounded border-[hsl(210,8%,85%);] vx:w-full '>
                                <h2 className='py-[12px] px-[15px] bg-[hsl(210,8%,97.5%);] text-[hsl(210,8%,35%)] text-[15px]   '>
                                    Watched Tags
                                </h2>
                                <div className='py-[16px] px-[15px] border-t border-[hsl(210,8%,85%);]  '>
                                    <div className='mx-auto items-center flex flex-col text-center m-[calc(16px/2*-1);] '>
                                        <div className='my-[calc(16px/2);] block'>
                                            돋보기 그림
                                        </div>
                                        <p className='my-[calc(16px/2);] block text-[hsl(210,8%,35%)] text-[13px]'>
                                            Watch tags to curate your list of questions.
                                        </p>
                                        <Link to='/' className='my-[calc(16px/2);] block text-[12px] text-[hsl(205,47%,42%)] bg-[hsl(205,46%,92%)] border border-transparent rounded-md p-2 '>
                                            Watch a tag
                                        </Link>
                                    </div>
                                </div>
                            </div>
                            <div className='mb-4 relative bg-white border rounded border-[hsl(210,8%,85%);] vx:w-full '>
                                <h2 className='py-[12px] px-[15px] bg-[hsl(210,8%,97.5%);] text-[hsl(210,8%,35%)] text-[15px]   '>
                                    Ignored Tags
                                </h2>
                                <div className='py-[16px] px-[15px] border-t border-[hsl(210,8%,85%);]'>
                                    <div className='mx-auto items-center flex flex-col text-center m-[calc(16px/2*-1);] '>
                                            <button className='my-[calc(16px/2);] block text-[12px] text-[hsl(205,47%,42%)] bg-[hsl(205,46%,92%)] border border-transparent rounded-md p-2 '>
                                                Add an Ignored Tags
                                            </button>
                                    </div>
                                </div>
                            </div>
                    </div>
                </div>
            </div>
        </>
    )



}

