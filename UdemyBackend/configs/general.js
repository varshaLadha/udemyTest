exports.jwtConfig = {
    secret : 'udemy'
}

exports.userAttributes = [
    'userName',
    'email',
    'loginType'
]

exports.categoryAttributes = [
    'id',
    'categoryName',
    'imageUrl'
]

exports.subCategoryAttributes = [
    'id',
    'categoryId',
    'subCategoryName'
]

exports.courseAttributes = [
    'id',
    'subCategoryId',
    'title',
    'tag',
    'instructorName',
    'rating',
    'peopleRated',
    'averageRating',
    'cost',
    'imageUrl'
]

exports.courseDetailAttributes = [
    'id',
    'courseId',
    'shortDescription',
    'description',
    'totalLearningHours',
    'learning',
    'requirements',
    'totalChapters',
    'previewVideoURL',
    'quiz',
    'articles',
    'assignments',
    'lifeTimeAccess',
    'access',
    'certification',
    'supportFiles'
]

exports.chapterAttributes = [
    'id',
    'courseDetailId',
    'title'
]