import React, { useEffect, useRef, useState  } from 'react';

function MyEditor({ onContentChange }) {
    const editorRef = useRef();
    const [editor, setEditor] = useState(null);

    useEffect(() => {
        if (window.ClassicEditor && editorRef.current) {
          window.ClassicEditor
            .create(editorRef.current)
            .then(newEditor => {
              setEditor(newEditor);
              newEditor.model.document.on('change:data', () => {
                onContentChange(newEditor.getData());
              });
            })
            .catch(error => {
              console.error(error);
            });
        }
    
        return () => {
          if (editor) {
            editor.destroy();
          }
        };
      }, [onContentChange]);

  return <div ref={editorRef}></div>;
}

export default MyEditor;