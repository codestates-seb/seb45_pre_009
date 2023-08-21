import { useParams } from 'react-router-dom';
import React, { useEffect } from 'react';
import { useSelector, useDispatch } from 'react-redux';
import { fetchData } from '../slicer/main';
import Sidebar from '../components/Sidebar/Sidebar';
import moment from 'moment-timezone';

function QuestionPage() {
    const { id } = useParams();
    const dispatch = useDispatch();
    const question = useSelector((state) => state.data.question);
    const status = useSelector((state) => state.data.status);

    useEffect(() => {
    if (status === 'idle') {
        dispatch(fetchData(`questions/${id}`));
    }
    }, [status, dispatch, id]);

    console.log(question);

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
                                            ? moment
                                                .utc([
                                                question.createdAt[0],
                                                question.createdAt[1] - 1,
                                                question.createdAt[2],
                                                question.createdAt[3],
                                                question.createdAt[4],
                                                question.createdAt[5],
                                                ])
                                                .tz("Asia/Seoul")
                                                .fromNow()
                                            : "Loading..."}
                                    </span>
                                </div>
                                <div className='mb-[8px] flex-nowrap  '>
                                    <span className='mr-[2px] text-[hsl(210,8%,45%)]'>Viewed</span>
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