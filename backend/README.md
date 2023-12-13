#docker
Instruction:
    For developer:
        1. Open project "backend"
        2. Build image
            docker build -t [IMAGE_NAME] [DOCKERFILE_PATH]
    
    For customer:
        1. Somehow get image
        2. Run container
            docker run -p [LOCAL_PORT]:[CONTAINER_PORT] [IMAGE_NAME]
        3. Open browser
        4. URL targer localhost:[LOCAL_PORT]

[DOCKERFILE_PATH]: .