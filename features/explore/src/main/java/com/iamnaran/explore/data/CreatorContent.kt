package com.iamnaran.explore.data

import androidx.media3.common.MediaItem

data class CreatorContent(
    val caption: String,
    val isPlaying: Boolean,
    val totalViews: Long = 0,
    val totalLikes: Long = 0,
    val totalComments: Long = 0,
    val videoUrl: String, val thumbnailUrl: String,
    val creatorContentType: CreatorContentType = CreatorContentType.TYPE_UNKNOWN
)

enum class CreatorContentType {
    TYPE_UNKNOWN,
    TYPE_PHOTOS,
    TYPE_STORY,
    TYPE_VIDEO,
}

fun List<CreatorContent>.toMediaUriItems(): List<MediaItem> {
    return map {
        MediaItem.fromUri(it.videoUrl)
    }
}


fun generateList(): List<CreatorContent> {
    return listOf(
        CreatorContent(
            caption = "Big Buck Bunny tells the story of a giant rabbit with a heart bigger than himself. When one sunny day three rodents rudely harass him, something snaps... and the rabbit ain't no bunny anymore! In the typical cartoon tradition he prepares the nasty rodents a comical revenge.\n\nLicensed under the Creative Commons Attribution license\nhttp://www.bigbuckbunny.org",
            isPlaying = false,  // Initial state: Not playing
            videoUrl = "https://www.sample-videos.com/video321/mp4/720/big_buck_bunny_720p_10mb.mp4",
            thumbnailUrl = "",
            creatorContentType = CreatorContentType.TYPE_VIDEO
        ),
        CreatorContent(
            caption = "Big Buck Bunny tells the story of a giant rabbit with a heart bigger than himself. When one sunny day three rodents rudely harass him, something snaps... and the rabbit ain't no bunny anymore! In the typical cartoon tradition he prepares the nasty rodents a comical revenge.\n\nLicensed under the Creative Commons Attribution license\nhttp://www.bigbuckbunny.org",
            isPlaying = false,  // Initial state: Not playing
            videoUrl = "https://videos.pexels.com/video-files/31378538/13389401_1080_1920_25fps.mp4",
            thumbnailUrl = "https://images.pexels.com/videos/31378538/pexels-photo-31378538.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
            creatorContentType = CreatorContentType.TYPE_VIDEO
        ),
        CreatorContent(
            caption = "The first Blender Open Movie from 2006",
            isPlaying = false,  // Initial state: Not playing
            videoUrl = "https://videos.pexels.com/video-files/2795405/2795405-uhd_1440_2560_25fps.mp4",
            thumbnailUrl = "https://images.pexels.com/videos/2795405/free-video-2795405.jpg?auto=compress&cs=tinysrgb&dpr=1&w=500",
            creatorContentType = CreatorContentType.TYPE_VIDEO
        ),
        CreatorContent(
            caption = "HBO GO now works with Chromecast -- the easiest way to enjoy online video on your TV. For when you want to settle into your Iron Throne to watch the latest episodes. For $35.\nLearn how to use Chromecast with HBO GO and more at google.com/chromecast.",
            isPlaying = false,  // Initial state: Not playing
            videoUrl = "https://videos.pexels.com/video-files/2795394/2795394-sd_360_640_25fps.mp4",
            thumbnailUrl = "images/ForBiggerBlazes.jpg",
            creatorContentType = CreatorContentType.TYPE_VIDEO
        ),
        CreatorContent(
            caption = "Introducing Chromecast. The easiest way to enjoy online video and music on your TV—for when Batman's escapes aren't quite big enough. For $35. Learn how to use Chromecast with Google Play Movies and more at google.com/chromecast.",
            isPlaying = false,  // Initial state: Not playing
            videoUrl = "https://videos.pexels.com/video-files/4040918/4040918-uhd_1440_2732_25fps.mp4",
            thumbnailUrl = "images/ForBiggerEscapes.jpg",
            creatorContentType = CreatorContentType.TYPE_VIDEO
        ),
        CreatorContent(
            caption = "Introducing Chromecast. The easiest way to enjoy online video and music on your TV. For $35. Find out more at google.com/chromecast.",
            isPlaying = false,  // Initial state: Not playing
            videoUrl = "https://videos.pexels.com/video-files/6507676/6507676-hd_1080_1920_25fps.mp4",
            thumbnailUrl = "images/ForBiggerFun.jpg",
            creatorContentType = CreatorContentType.TYPE_VIDEO
        ),
        CreatorContent(
            caption = "Introducing Chromecast. The easiest way to enjoy online video and music on your TV—for the times that call for bigger joyrides. For $35. Learn how to use Chromecast with YouTube and more at google.com/chromecast.",
            isPlaying = false,  // Initial state: Not playing
            videoUrl = "https://videos.pexels.com/video-files/31378538/13389401_1080_1920_25fps.mp4",
            thumbnailUrl = "images/ForBiggerJoyrides.jpg",
            creatorContentType = CreatorContentType.TYPE_VIDEO
        ),
        CreatorContent(
            caption = "Introducing Chromecast. The easiest way to enjoy online video and music on your TV—for when you want to make Buster's big meltdowns even bigger. For $35. Learn how to use Chromecast with Netflix and more at google.com/chromecast.",
            isPlaying = false,  // Initial state: Not playing
            videoUrl = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerMeltdowns.mp4",
            thumbnailUrl = "images/ForBiggerMeltdowns.jpg",
            creatorContentType = CreatorContentType.TYPE_VIDEO
        ),
        CreatorContent(
            caption = "Sintel is an independently produced short film, initiated by the Blender Foundation as a means to further improve and validate the free/open source 3D creation suite Blender.",
            isPlaying = false,  // Initial state: Not playing
            videoUrl = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/Sintel.mp4",
            thumbnailUrl = "images/Sintel.jpg",
            creatorContentType = CreatorContentType.TYPE_VIDEO
        ),
        CreatorContent(
            caption = "Smoking Tire takes the all-new Subaru Outback to the highest point we can find in hopes our customer-appreciation Balloon Launch will get some free T-shirts into the hands of our viewers.",
            isPlaying = false,  // Initial state: Not playing
            videoUrl = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/SubaruOutbackOnStreetAndDirt.mp4",
            thumbnailUrl = "images/SubaruOutbackOnStreetAndDirt.jpg",
            creatorContentType = CreatorContentType.TYPE_VIDEO
        ),
        CreatorContent(
            caption = "Tears of Steel was realized with crowd-funding by users of the open source 3D creation tool Blender.",
            isPlaying = false,  // Initial state: Not playing
            videoUrl = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/TearsOfSteel.mp4",
            thumbnailUrl = "images/TearsOfSteel.jpg",
            creatorContentType = CreatorContentType.TYPE_VIDEO
        ),
        CreatorContent(
            caption = "The Smoking Tire heads out to Adams Motorsports Park in Riverside, CA to test the most requested car of 2010, the Volkswagen GTI.",
            isPlaying = false,  // Initial state: Not playing
            videoUrl = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/VolkswagenGTIReview.mp4",
            thumbnailUrl = "images/VolkswagenGTIReview.jpg",
            creatorContentType = CreatorContentType.TYPE_VIDEO
        ),
        CreatorContent(
            caption = "The Smoking Tire is going on the 2010 Bullrun Live Rally in a 2011 Shelby GT500, and posting a video from the road every single day!",
            isPlaying = false,  // Initial state: Not playing
            videoUrl = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/WeAreGoingOnBullrun.mp4",
            thumbnailUrl = "images/WeAreGoingOnBullrun.jpg",
            creatorContentType = CreatorContentType.TYPE_VIDEO
        ),
        CreatorContent(
            caption = "The Smoking Tire meets up with Chris and Jorge from CarsForAGrand.com to see just how far $1,000 can go when looking for a car.",
            isPlaying = false,  // Initial state: Not playing
            videoUrl = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/WhatCarCanYouGetForAGrand.mp4",
            thumbnailUrl = "images/WhatCarCanYouGetForAGrand.jpg",
            creatorContentType = CreatorContentType.TYPE_VIDEO
        )
    )
}
