export const isPostLikedByUser=(post, userId)=>{
    
    if (!post?.likes || !Array.isArray(post.likes)) {
        return false;
    }
    for(let item of post.likes){
        if(item===userId)
            return true;
    }
    return false;
}

export const isCommentLikedByUser=(comment, userId)=>{
    for(let item of comment.likes){
        if(item.id===userId)
            return true;
    }
    return false;
}

export const isSavedPost=(user, postId)=>{
    for(let item of user.savedPosts){
        if(item===postId)
            return true;
    }
    return false;
}

export const isFollowingt=(reqUser, user2)=>{
    if(reqUser && user2){
        for(let item of user2.follower){
            if(reqUser.id===item.id)
                return true;
        }
    }
    return false;
}

export const timeDifference=(timestamp)=>{
    const date=new Date(timestamp);
    const diff=Date.now()-date.getTime();

    const seconds=Math.floor(diff/1000);

    const minutes=Math.floor(seconds/60);
    const hours=Math.floor(minutes/60);
    const days=Math.floor(hours/24);
    const weeks=Math.floor(days/7);

    if(weeks>0){
        return weeks + " week"+ (weeks===1?"":"s")+" ago";
    }
    else if(days>0){
        return days + " days"+ (days===1?"":"s")+" ago";
    }
    else if(hours>0){
        return hours + " hours"+ (hours===1?"":"s")+" ago";
    }
    else if(minutes>0){
        return minutes + " minutes"+ (minutes===1?"":"s")+" ago";
    }
    else if(seconds>0){
        return seconds + " seconds"+ (seconds===1?"":"s")+" ago";
    }
    
}