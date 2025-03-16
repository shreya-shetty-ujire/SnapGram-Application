export const uploadToCloudnary = async(image) => {
    if(image){
        const data = new FormData()
        data.append("file",image)
        data.append("upload_preset", "snapgram")
        data.append("cloud_name", "dade62mq5")

        const res= await fetch("https://api.cloudinary.com/v1_1/dade62mq5/image/upload",{
            method: "post",
            body: data
        })

        const fileData = await res.json();

        console.log("fileData ", fileData)
        return fileData.url.toString();

        

    }
}