import React, { useState, useEffect } from 'react';
import { useSelector, useDispatch } from 'react-redux';
import { postData } from '../../slicer/main';

export function CommentInput( { questionId, answerId  }) {
    const dispatch = useDispatch();

    const [showInput, setShowInput] = useState(false);

    const [commentContent, setCommentContent] = useState('');

    const toggleInput = (e) => {
    e.preventDefault(); // 링크의 기본 동작을 방지합니다.
    setShowInput(!showInput); // showInput 상태를 토글합니다.
    };

    const handleCommentChange = (e) => {
        setCommentContent(e.target.value);
    };

    const handleSubmitcomment = () => {
        const myAnswer = {
            commentContent
        };
        console.log(myAnswer);
        dispatch(postData({ path: `questions/${questionId}/answers/${answerId}/comments`, data: myAnswer }));
    };

    // /questions/{question-id}/answers/{answer-id}/comments endpoint 형식

    return (
        <div id='comment' className='pr-[16px] grid-cols-[2] w-auto '>
            <div className=' text-[13px] text-gray-400 '>
                <button className='p-[0,3px,2px] cursor-pointer' onClick={toggleInput}>
                    Add a comment
                </button>
                {showInput && (
                    <div>
                        <input onChange={handleCommentChange} type='text' placeholder='Enter your comment here...' />
                        <button onClick={handleSubmitcomment} >send</button>
                    </div>
                )}
            </div>
        </div>
    );
}